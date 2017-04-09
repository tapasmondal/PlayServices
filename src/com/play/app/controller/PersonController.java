package com.play.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.play.app.constant.PlayAppConstant;
import com.play.app.dto.GameDTO;
import com.play.app.dto.PersonDTO;
import com.play.app.dto.PersonGameDTO;
import com.play.app.dto.PersonTournamentDTO;
import com.play.app.dto.TeamPerformanceDTO;
import com.play.app.dto.UserDTO;
import com.play.app.model.AdminUpdateModel;
import com.play.app.model.PersonDashboardModel;
import com.play.app.model.PersonGameModel;
import com.play.app.model.PersonModel;
import com.play.app.model.VoteInfoModel;
import com.play.app.service.PlayInfoService;
import com.play.app.service.PlayUpdateService;

@RestController
@RequestMapping("/public-api/infoService")
public class PersonController  {
	
	@Autowired
	PlayInfoService playInfoService;
	
	@Autowired
	PlayUpdateService playUpdateService;

	
	@RequestMapping(value = "/listAllPerson", method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> listAllPerson() {
		
		List<PersonDTO> persons = playInfoService.findAllPersons();
		
		if (persons.isEmpty()) {
			return new ResponseEntity<List<PersonDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<PersonDTO>>(persons, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Integer id) {
		
		PersonDTO persons = playInfoService.findPerson(id);
		
		if (persons==null) {
			return new ResponseEntity<PersonDTO>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<PersonDTO>(persons, HttpStatus.OK);
	}
	
	//List<PersonTournamentDTO> findPersonTournament(Integer personId, Integer tournamentId);
	@RequestMapping(value = "/person/{id}/dashboard", method = RequestMethod.GET)
	public ResponseEntity<PersonDashboardModel> getPersonDashboardModel(@PathVariable("id") Integer id){
		
		PersonDashboardModel model=playInfoService.getPersonDashboardInfo(id);
		
		if (model==null) {
			return new ResponseEntity<PersonDashboardModel>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<PersonDashboardModel>(model, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/person/{id}/gamestats/tournament/{tournamentId}/game/{gameno}", method = RequestMethod.GET)
	public ResponseEntity<List<PersonGameModel>> getGameStats(@PathVariable("id") Integer id,@PathVariable("tournamentId") Integer tournamentId,@PathVariable("gameno") Integer gameno){
		
		List<PersonGameModel> personGameModels=playInfoService.findPersonsGame(tournamentId, gameno);
		
		if (personGameModels==null) {
			return new ResponseEntity<List<PersonGameModel>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<PersonGameModel>>(personGameModels, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/person/{id}/voteview/tournament/{tournamentId}/schedule/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<PersonGameModel>> getVoteView(@PathVariable("id") Integer id,@PathVariable("tournamentId") Integer tournamentId,@PathVariable("date") String date){
		
		System.out.println("date as string:"+date);
		Calendar calendar = new GregorianCalendar();
		Date startDate=calendar.getTime();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

        	startDate = formatter.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
        
        calendar.setTime(startDate);
		Date endDate =new DateTime()
				.withYear(calendar.get(Calendar.YEAR))
				.withMonthOfYear(calendar.get(Calendar.MONTH)+1)
				.withDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH))
			    .withHourOfDay(23)
			    .withMinuteOfHour(59)
			    .withSecondOfMinute(59).toDate(); 
		System.out.println("month:");
		System.out.println(startDate +" > "+endDate);
		
		List<PersonGameModel> models=playInfoService.findAllPersonsGameByDate(id,tournamentId, startDate, endDate);
		
		if (models==null) {
			return new ResponseEntity<List<PersonGameModel>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<PersonGameModel>>(models, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/person/{id}/leaderboard/tournament/{tournamentId}", method = RequestMethod.GET)
	public ResponseEntity<List<PersonTournamentDTO>> getLeaderboardView(@PathVariable("id") Integer id,@PathVariable("tournamentId") Integer tournamentId){
		
		List<PersonTournamentDTO> personTournamentDTOs=playInfoService.findPersonTournament(null, tournamentId);
		
		if (personTournamentDTOs==null) {
			return new ResponseEntity<List<PersonTournamentDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<PersonTournamentDTO>>(personTournamentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/person/{id}/leaderboardperson/tournament/{tournamentId}", method = RequestMethod.GET)
	public ResponseEntity<List<PersonGameModel>> getLeaderboardPersonHistoryView(@PathVariable("id") Integer id,@PathVariable("tournamentId") Integer tournamentId){
		
		List<PersonGameModel> models=playInfoService.findAllPersonsGame(id,tournamentId);
		
		if (models==null) {
			return new ResponseEntity<List<PersonGameModel>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<PersonGameModel>>(models, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "updatedPersonVote", method = RequestMethod.POST)
	public ResponseEntity<VoteInfoModel> updatedPersonVote(@RequestBody VoteInfoModel voteInfoModel) {
		System.out.println("updated vote: " + voteInfoModel.toString());

		
		voteInfoModel.setVotingTime(Calendar.getInstance().getTime());
		voteInfoModel=playUpdateService.updatePersonsGameByPerson(voteInfoModel);

		return new ResponseEntity<VoteInfoModel>(voteInfoModel, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "updatedAdminInfo", method = RequestMethod.POST)
	public ResponseEntity<AdminUpdateModel> updatedPersonVote(@RequestBody AdminUpdateModel adminUpdatemodel) {
		
		
		System.out.println("Admin update: " + adminUpdatemodel.toString());

		
		adminUpdatemodel.setUpdatedDate(Calendar.getInstance().getTime());
		
		
		if(adminUpdatemodel.getGameStatus().equals(PlayAppConstant.SCHEDULED))
		{
			if (adminUpdatemodel.getGameLockDelayBy()!=null && adminUpdatemodel.getGameLockDelayBy().intValue()>0)
			adminUpdatemodel.setGameLockStatus(false);
			
			adminUpdatemodel=playUpdateService.updateGameByAdmin(adminUpdatemodel);
		}
		//calculate point, update winner and game status
		else {
			adminUpdatemodel.setGameLockStatus(true);
			//if person has not participated, first add default point to loosing team 
			playUpdateService.updatedGamePersonforNonParticipation(adminUpdatemodel);
			//then calculated and updated all data
			adminUpdatemodel=playUpdateService.updateGameStatusByAdmin(adminUpdatemodel);
		}
		
		return new ResponseEntity<AdminUpdateModel>(adminUpdatemodel, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/person/{id}/teamperfomance/tournament/{tournamentId}/team/{teamId}", method = RequestMethod.GET)
	public ResponseEntity<TeamPerformanceDTO> getTeamPerfomanceByTeam(@PathVariable("id") Integer id,@PathVariable("tournamentId") Integer tournamentId,@PathVariable("teamId") Integer teamId){
		
		TeamPerformanceDTO models=playInfoService.findTeamPerformance(tournamentId,teamId);
		Calendar calendar = new GregorianCalendar();
		List<GameDTO> games=playInfoService.findAllGameByTeam(tournamentId, teamId, calendar.getTime() );
		
			
		
		if (models==null) {
			
			return new ResponseEntity<TeamPerformanceDTO>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		else{
			models.setGames(games);
		}
		return new ResponseEntity<TeamPerformanceDTO>(models, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/person/{id}/teamperfomance/tournament/{tournamentId}", method = RequestMethod.GET)
	public ResponseEntity<List<TeamPerformanceDTO>> getTeamPerfomanceByTournament(@PathVariable("id") Integer id,@PathVariable("tournamentId") Integer tournamentId){
		
		List<TeamPerformanceDTO> models=playInfoService.findAllTeamPerformance(tournamentId);
		
		if (models==null) {
			return new ResponseEntity<List<TeamPerformanceDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<TeamPerformanceDTO>>(models, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "updatedPersonData", method = RequestMethod.POST)
	public ResponseEntity<PersonModel> updatedPersonStatus(@RequestBody PersonModel personModel) {
		System.out.println("Before :updated personStatus : " + personModel.toString());
		personModel=playUpdateService.updatePersonDataByAdmin(personModel);
		System.out.println("After :updated personStatus : " + personModel.toString());
		
		return new ResponseEntity<PersonModel>(personModel, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/person/{id}/tournament/{tournamentId}/listAllPersonDetails", method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> listAllPerson(@PathVariable("tournamentId") Integer tournamentId) {
		
		List<PersonDTO> persons = playInfoService.findAllPersons(tournamentId);
		
		if (persons.isEmpty()) {
			return new ResponseEntity<List<PersonDTO>>(HttpStatus.NOT_FOUND); // HttpStatus.NOT_FOUND
		}
		
		return new ResponseEntity<List<PersonDTO>>(persons, HttpStatus.OK);
	}
	
	
}