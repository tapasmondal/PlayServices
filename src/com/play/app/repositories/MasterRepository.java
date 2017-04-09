package com.play.app.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.Master;

public interface MasterRepository extends CrudRepository<Master, Long> {

	Master findByMasterTypeAndKey(String masterType, String key);

	List<Master> findByMasterType(String masterType);
	
	List<Master> findByMasterTypeIn(Collection<String> masterTypeList);
}
