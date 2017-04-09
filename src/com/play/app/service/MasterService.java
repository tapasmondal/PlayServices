package com.play.app.service;

import java.util.Collection;
import java.util.List;

import com.play.app.dto.MasterDTO;

public interface MasterService {

	MasterDTO findById(long id);

	void saveMaster(MasterDTO master);

	void deleteMasterById(long id);

	List<MasterDTO> findAllMasters();
	
	List<MasterDTO> findByMasterType(String masterType);

	void deleteAllMasters();

	public boolean isMasterExist(MasterDTO master);

	MasterDTO findByMasterTypeAndKey(String masterType, String key);

	List<MasterDTO> findMastersByTypes(Collection<String> masterTypeList);

	String getPropertyByMasterTypeAndKey(String masterType, String key);

	List<MasterDTO> getMasterListByMasterType(String masterType);

}
