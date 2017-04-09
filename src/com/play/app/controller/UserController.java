package com.play.app.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.play.app.dto.PersonDTO;
import com.play.app.dto.UserDTO;
import com.play.app.facade.SecurityUtils;
import com.play.app.model.UserRegistrationModel;
import com.play.app.service.UserService;

@RestController
@RequestMapping("/public-api/user")
public class UserController {

	@Autowired
	UserService userService;

	// -------------------Retrieve All
	// Users--------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User--------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
		System.out.println("Fetching User with id " + id);
		UserDTO user = userService.findByUserId(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "getLoggedInUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getLoggedInUser() {
		UserDTO user = SecurityUtils.getLoggedInUserDTO();
		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> createUser(@RequestBody UserRegistrationModel userModel)
			throws IOException {

		

		UserDTO user = new UserDTO();

		//user.setUserId(request.getParameter("userId"));
		//auto generated
		
		user.setEmail(userModel.getUserName()+"@abc.com");
		user.setUserName(userModel.getUserName());
		user.setUserRoles("Person");
		user.setAdmin(false);
		user.setStatus("Active");
		user.setEnabled(true);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setToken(userModel.getToken());
		user.setTeamId(userModel.getTeamId());
		user.setTournamentId(userModel.getTournamentId());
		user =userService.saveUser(user);
		user.setPassword("");
		return new ResponseEntity<UserDTO>(user,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> resetPassword(@RequestBody UserRegistrationModel userModel)
			throws IOException {

		

		UserDTO user = new UserDTO();		
		user.setUserName(userModel.getUserName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setToken(userModel.getToken());
		user =userService.resetPassword(user);
		user.setPassword("");
		return new ResponseEntity<UserDTO>(user,HttpStatus.CREATED);
	}


	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("UserController.main()"+passwordEncoder.encode("123"));
	}
	
	// ------------------- Update a User
	// --------------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO user) {
		System.out.println("Updating User " + id);

		UserDTO currentUser = userService.findByUserId(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		BeanUtils.copyProperties(user, currentUser);

		userService.saveUser(currentUser);
		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}


}