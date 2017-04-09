package com.play.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.PersonTournament;
import com.play.app.entity.PersonTournamentId;
import com.play.app.entity.PersonTournamentTemp;

public interface PersonTournamentRepositoryTemp extends CrudRepository<PersonTournamentTemp, PersonTournamentId> {

	
}
