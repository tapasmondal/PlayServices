package com.play.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.PersonTournament;
import com.play.app.entity.PersonTournamentId;

public interface PersonTournamentRepository extends CrudRepository<PersonTournament, PersonTournamentId> {

	List<PersonTournament> findByPersonIdAndTournamentId(Integer personId,Integer tournamentId);
	
	List<PersonTournament> findByPersonId(Integer personId);
	
	List<PersonTournament> findByTournamentId(Integer tournament);
}
