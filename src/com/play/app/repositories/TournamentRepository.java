package com.play.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {

	Tournament findByTournamentId(Integer tournament);

	
}
