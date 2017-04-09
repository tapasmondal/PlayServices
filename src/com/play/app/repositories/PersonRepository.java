package com.play.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	Person findByPersonId(Integer personId);
	
	Person findByToken(String token);
	
	Person findByPersonIdIn(List<Integer> personIds);
}
