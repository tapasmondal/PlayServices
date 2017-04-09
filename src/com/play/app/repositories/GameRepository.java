package com.play.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.Game;
import com.play.app.entity.Tournament;

public interface GameRepository extends CrudRepository<Game, Integer> {

	Game findByGameNo(Integer gameNo);
	
	Game findByGameTournamentSequenceNo(Integer gameTournamentSequenceNo);

	List<Game> findByScheduleBetween(Date fromDate, Date toDate);

	List<Game> findByTournament(Tournament tournament);

	List<Game> findByScheduleAfterAndTournamentAndTeamAIdOrTeamBIdOrderByGameNoAsc(Date schedule,Tournament tournament,Integer teamAId,Integer teamBId);
	
	List<Game> findByTournamentAndTeamAIdOrTeamBIdOrderByGameNoAsc(Tournament tournament,Integer teamAId,Integer teamBId);

}
