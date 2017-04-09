package com.play.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.TeamPerformance;
import com.play.app.entity.TeamPerformanceId;

public interface TeamPerformanceRepository extends CrudRepository<TeamPerformance, TeamPerformanceId> {

	List<TeamPerformance> findByTournamentId(Integer tournament);
	List<TeamPerformance> findByTournamentIdAndTeamId (Integer tournament,Integer teamId);
	
}
