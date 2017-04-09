package com.play.app.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.play.app.dto.MasterDTO;
import com.play.app.service.MasterService;

@RestController
@RequestMapping("/public-api/master")
public class MasterController  {
	
	@Autowired
	MasterService masterService;

	// -------------------Retrieve All
	// Masters--------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MasterDTO>> listAllMasters() {
		
		List<MasterDTO> masters = masterService.findAllMasters();
		
		if (masters.isEmpty()) {
			return new ResponseEntity<List<MasterDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<MasterDTO>>(masters, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listAllMastersByType/{masterType}", method = RequestMethod.GET)
	public ResponseEntity<List<MasterDTO>> listAllMastersByType(@PathVariable("masterType") String masterType) {
		
		List<MasterDTO> masters = masterService.findByMasterType(masterType);
		
		if (masters.isEmpty()) {
			return new ResponseEntity<List<MasterDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<MasterDTO>>(masters, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Master--------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MasterDTO> getMaster(@PathVariable("id") long id) {
		System.out.println("Fetching Master with id " + id);
		MasterDTO master = masterService.findById(id);
		if (master == null) {
			System.out.println("MasterDTO with id " + id + " not found");
			return new ResponseEntity<MasterDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MasterDTO>(master, HttpStatus.OK);
	}

	// -------------------Create a
	// MasterDTO--------------------------------------------------------

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createMaster(@RequestBody MasterDTO master, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating MasterDTO " + master.getMasterType() + " : " + master.getKey());

		if (masterService.isMasterExist(master)) {
			System.out.println(
					"A MasterDTO with name " + master.getMasterType() + " : " + master.getKey() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		masterService.saveMaster(master);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/master/{id}").buildAndExpand(master.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a MasterDTO
	// --------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<MasterDTO> updateMaster(@PathVariable("id") long id, @RequestBody MasterDTO master) {
		System.out.println("Updating MasterDTO " + id);

		MasterDTO currentMaster = masterService.findById(id);

		if (currentMaster == null) {
			System.out.println("MasterDTO with id " + id + " not found");
			return new ResponseEntity<MasterDTO>(HttpStatus.NOT_FOUND);
		}

		BeanUtils.copyProperties(master, currentMaster);

		masterService.saveMaster(currentMaster);
		return new ResponseEntity<MasterDTO>(currentMaster, HttpStatus.OK);
	}

	// ------------------- Delete a MasterDTO
	// --------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MasterDTO> deleteMaster(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting MasterDTO with id " + id);

		MasterDTO master = masterService.findById(id);
		if (master == null) {
			System.out.println("Unable to delete. MasterDTO with id " + id + " not found");
			return new ResponseEntity<MasterDTO>(HttpStatus.NOT_FOUND);
		}

		masterService.deleteMasterById(id);
		return new ResponseEntity<MasterDTO>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Masters
	// --------------------------------------------------------

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<MasterDTO> deleteAllMasters() {
		System.out.println("Deleting All Masters");

		masterService.deleteAllMasters();
		return new ResponseEntity<MasterDTO>(HttpStatus.NO_CONTENT);
	}
	

}