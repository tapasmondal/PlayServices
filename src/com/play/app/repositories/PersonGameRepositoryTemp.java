package com.play.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.Game;
import com.play.app.entity.PersonGame;
import com.play.app.entity.PersonGameTemp;
import com.play.app.entity.PersoneGameId;

public interface PersonGameRepositoryTemp extends CrudRepository<PersonGameTemp, PersoneGameId> {

	}
