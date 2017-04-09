package com.play.app.service;

import java.util.Date;
import java.util.List;

import com.play.app.dto.GameDTO;
import com.play.app.dto.PersonDTO;
import com.play.app.dto.PersonGameDTO;
import com.play.app.dto.PersonTournamentDTO;
import com.play.app.dto.TeamPerformanceDTO;
import com.play.app.dto.TournamentDTO;
import com.play.app.model.PersonDashboardModel;
import com.play.app.model.PersonGameModel;

public interface PlayInfoService {


	
	List<PersonDTO> findAllPersons();
	
	PersonDTO findPerson(Integer personId );
	
	//any of these can be null but not both
	List<PersonTournamentDTO> findPersonTournament(Integer personId, Integer tournamentId);
	
	//Person Id can be null
	List<PersonGameModel> findPersonsGame(Integer tournamentId,Integer nextGameNo  );
	
	GameDTO findGame(Integer gameNo);
	
	List<GameDTO> findAllGame(Integer tournamentId);
	
	List<GameDTO> findAllGameByTeam(Integer tournamentId,Integer teamId,Date FromDate);
	
	List<TournamentDTO> findTournament(Integer year, boolean active);
	
	PersonDashboardModel getPersonDashboardInfo(Integer personId);

	List<PersonGameModel> findAllPersonsGameByDate(Integer personId, Integer tournamentId, Date fromDate, Date toDate);
	
	List<PersonGameModel> findAllPersonsGame(Integer personId, Integer tournamentId);
	
	TeamPerformanceDTO findTeamPerformance(Integer tournamentId,Integer teamId);
	
	List<TeamPerformanceDTO> findAllTeamPerformance(Integer tournamentId);

	List<PersonDTO> findAllPersons(Integer tournamentId);
}
