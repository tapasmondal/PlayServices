package com.play.app.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.play.app.dto.MasterDTO;
import com.play.app.entity.Master;
import com.play.app.repositories.MasterRepository;
import com.play.app.service.MasterService;
import com.play.app.type.MasterType;

@Service("masterService")
@Transactional
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterRepository masterRepository;

	ModelMapper modelMapper = new ModelMapper();

	private Map<String, List<MasterDTO>> masterTypeListMap = new HashMap<>();
	
	private Map<String, String> propertiesMap = new HashMap<>();

	@PostConstruct
	private void init() {
		loadMasterListCache();
		loadPropertiesCache();
	}

	public void loadMasterListCache() {
		masterTypeListMap = new HashMap<>();
		List<MasterDTO> masterDTOList = findAllMasters();
		for (MasterDTO masterDTO : masterDTOList) {
			List<MasterDTO> existingList = masterTypeListMap.get(masterDTO.getMasterType());
			if (CollectionUtils.isEmpty(existingList)) {
				existingList = new ArrayList<>();
			}
			existingList.add(masterDTO);
			masterTypeListMap.put(masterDTO.getMasterType(), existingList);
		}
	}
	
	public void loadPropertiesCache() {
		propertiesMap = new HashMap<>();
		Collection<String> masterTypeList = new ArrayList<>();
		masterTypeList.add(MasterType.CORE_SETTINGS.getMasterType());
		masterTypeList.add(MasterType.PAYMENT_GATEWAY_SETTINGS.getMasterType());
		masterTypeList.add(MasterType.PUSH_NOTIFICATIONS_SETTINGS.getMasterType());
		masterTypeList.add(MasterType.SMS_SETTINGS.getMasterType());
		masterTypeList.add(MasterType.EMAIL_SETTINGS.getMasterType());
		masterTypeList.add(MasterType.SERVICE_QUALITY_SETTINGS.getMasterType());
		
		List<MasterDTO> masterDTOList = findMastersByTypes(masterTypeList );
		for (MasterDTO masterDTO : masterDTOList) {
			propertiesMap.put(masterDTO.getMasterType()+"#"+masterDTO.getKey(), masterDTO.getValue());
		}
	}

	@Override
	public List<MasterDTO> getMasterListByMasterType(String masterType) {
		return masterTypeListMap.get(masterType);
	}
	
	@Override
	public String getPropertyByMasterTypeAndKey(String masterType, String key) {
		return propertiesMap.get(masterType+"#"+key);
	}

	@Override
	public MasterDTO findById(long id) {
		return modelMapper.map(masterRepository.findOne(id), MasterDTO.class);
	}

	@Override
	public MasterDTO findByMasterTypeAndKey(String masterType, String key) {
		return modelMapper.map(masterRepository.findByMasterTypeAndKey(masterType, key), MasterDTO.class);
	}

	@Override
	public void saveMaster(MasterDTO masterDTO) {
		masterRepository.save(modelMapper.map(masterDTO, Master.class));
	}

	@Override
	public void deleteMasterById(long id) {
		masterRepository.delete(id);
	}

	@Override
	public List<MasterDTO> findAllMasters() {
		List<Master> masterEntityList = (List<Master>) masterRepository.findAll();
		Type listType = new TypeToken<List<MasterDTO>>() {
		}.getType();
		List<MasterDTO> masterDTOList = modelMapper.map(masterEntityList, listType);
		return masterDTOList;
	}
	
	@Override
	public List<MasterDTO> findMastersByTypes(Collection<String> masterTypeList) {
		List<Master> masterEntityList = (List<Master>) masterRepository.findByMasterTypeIn(masterTypeList);
		Type listType = new TypeToken<List<MasterDTO>>() {
		}.getType();
		List<MasterDTO> masterDTOList = modelMapper.map(masterEntityList, listType);
		return masterDTOList;
	}

	@Override
	public void deleteAllMasters() {
		List<MasterDTO> masterList = findAllMasters();
		for (MasterDTO master : masterList) {
			deleteMasterById(master.getId());
		}
	}

	@Override
	public boolean isMasterExist(MasterDTO master) {

		if (!StringUtils.isEmpty(master.getMasterType()) && !StringUtils.isEmpty(master.getKey())) {
			if (findByMasterTypeAndKey(master.getMasterType(), master.getKey()) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<MasterDTO> findByMasterType(String masterType) {
		List<Master> masterEntityList = (List<Master>) masterRepository.findByMasterType(masterType);
		Type listType = new TypeToken<List<MasterDTO>>() {
		}.getType();
		List<MasterDTO> masterDTOList = modelMapper.map(masterEntityList, listType);
		return masterDTOList;
	}

}
