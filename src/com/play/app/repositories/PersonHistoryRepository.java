package com.play.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.PersonHistory;

public interface PersonHistoryRepository extends CrudRepository<PersonHistory, Integer> {
	
}
