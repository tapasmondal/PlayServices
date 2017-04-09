package com.play.app.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.app.dto.GameDTO;
import com.play.app.dto.PersonDTO;
import com.play.app.dto.PersonGameDTO;
import com.play.app.dto.PersonTournamentDTO;
import com.play.app.dto.TeamPerformanceDTO;
import com.play.app.dto.TournamentDTO;
import com.play.app.entity.Game;
import com.play.app.entity.Person;
import com.play.app.entity.PersonGame;
import com.play.app.entity.PersonTournament;
import com.play.app.entity.Team;
import com.play.app.entity.TeamPerformance;
import com.play.app.entity.TeamPerformanceId;
import com.play.app.entity.Tournament;
import com.play.app.model.PersonDashboardModel;
import com.play.app.model.PersonGameModel;
import com.play.app.model.UIModelMapper;
import com.play.app.repositories.GameRepository;
import com.play.app.repositories.PersonGameRepository;
import com.play.app.repositories.PersonRepository;
import com.play.app.repositories.PersonTournamentRepository;
import com.play.app.repositories.TeamPerformanceRepository;
import com.play.app.repositories.TeamRepository;
import com.play.app.repositories.TournamentRepository;
import com.play.app.service.PlayInfoService;

@Service("PlayInfoService")
public class PlayInfoServiceImpl implements PlayInfoService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonTournamentRepository personTournamentRepository;
	
	@Autowired
	private PersonGameRepository personGameRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
		
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamPerformanceRepository teamPerformanceRepository;
	
	
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<PersonDTO> findAllPersons() {
		Iterable<Person> personEntityList = personRepository.findAll();
		Type listType = new TypeToken<List<PersonDTO>>() {
		}.getType();
		List<PersonDTO> personDTOList = modelMapper.map(personEntityList, listType);

		return personDTOList;
	}
	
	@Override
	public List<PersonDTO> findAllPersons(Integer tournamentId) {
		
		List<PersonDTO> personDTOList=new ArrayList<PersonDTO>();
		if(tournamentId!=null){
			
			List<PersonTournament> list=personTournamentRepository.findByTournamentId(tournamentId);
			if(list!=null && list.size()>0){
			  
				List<Person> personEntityList = new ArrayList<Person>();
				for (PersonTournament personTournament : list) {
					personEntityList.add(personTournament.getPerson());
				}
				
				Type listType = new TypeToken<List<PersonDTO>>() {
				}.getType();
				personDTOList = modelMapper.map(personEntityList, listType);
			}
		}
		return personDTOList;
	}

	@Override
	public PersonDTO findPerson(Integer personId) {
		
		Person person = personRepository.findByPersonId(personId);
		PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

		return personDTO;
	}

	@Override
	public List<PersonTournamentDTO> findPersonTournament(Integer personId, Integer tournamentId) {
		List<PersonTournament> list=null;
		
		if(personId!=null  && tournamentId!=null){
			list=personTournamentRepository.findByPersonIdAndTournamentId(personId, tournamentId);
		}
		else if(personId!=null){
			 list=personTournamentRepository.findByPersonId(personId);
		}
		else if(tournamentId!=null){
			list=personTournamentRepository.findByTournamentId(tournamentId);
		}
		
		if(list!=null){
			Collections.sort(list);
		}
		
		Type listType = new TypeToken<List<PersonTournamentDTO>>() {
		}.getType();
		List<PersonTournamentDTO> personTournamentDTOList = modelMapper.map(list, listType);

		
		return personTournamentDTOList;
	}

	//Person Id can be null
	@Override
	public List<PersonGameModel> findPersonsGame( Integer tournamentId, Integer nextGameNo) {
		
       List<PersonGame> list=null;
       List<PersonGameModel> models=new ArrayList<PersonGameModel>();
		
        Game game = gameRepository.findByGameTournamentSequenceNo(nextGameNo);
		
		if(tournamentId!=null &&  game!=null){
			list=personGameRepository.findByTournamentIdAndGameNoOrderByPersonIdAsc(tournamentId,game.getGameNo());
			for (PersonGame personGame : list) {
				PersonGameModel personGameModel=new PersonGameModel();
				models.add(personGameModel);
				UIModelMapper.update(personGameModel, personGame);
			}
			
		}
		
		

		return models;
	}

	@Override
	public List<PersonGameModel> findAllPersonsGameByDate(Integer personId,Integer tournamentId, Date fromDate, Date toDate) {
		
		List<PersonGame> list=null;
		List<PersonGameModel> models=new ArrayList<PersonGameModel>();
		
		
		if(personId!=null && tournamentId!=null && fromDate!=null && toDate!=null ){
		
			
			
				 List<Game> gameList = gameRepository.findByScheduleBetween(fromDate, toDate);
				 List<Integer> gameNos=new ArrayList<Integer>();
				 for (Game game : gameList) {
					 gameNos.add(game.getGameNo());
				}
				 
				if(gameList!=null){
					
					
					
					list=personGameRepository.findByPersonIdAndTournamentIdAndGameNoIn( personId,tournamentId, gameNos);
				
					if(list!=null && list.size()>0){
					
						
						
							for (PersonGame personGame : list) {
								PersonGameModel personGameModel=new PersonGameModel();
								models.add(UIModelMapper.update(personGameModel, personGame));
								List<PersonGame> personGamelist=personGameRepository.findByTournamentIdAndGameNo(tournamentId,personGame.getGame().getGameNo());
						        updateModelValue(personGamelist,personGameModel);
							}
							
							List<Game> gameListTemp=new ArrayList<Game>();
							for (Game game : gameList) {
							 boolean found=false;
								 for (PersonGameModel personGameModel : models) {
									 if(personGameModel.getGameNo().equals(game.getGameNo())){
										 found=true;
										 break;
									 }
								 }
							     //if game data is not populated in the model 
								 if(!found){
									 gameListTemp.add(game);
									// models=populatePersonGameModel(personId, tournamentId, models, gameList);
								 }
						    }
							
							//populate details for remaining game no 
							models=populatePersonGameModel(personId, tournamentId, models, gameListTemp);
							
					}
					else{
						
					 models=populatePersonGameModel(personId, tournamentId, models, gameList);
				  }
					
				
			 }
		
		}

		return models;
	}

	private List<PersonGameModel> populatePersonGameModel(Integer personId, Integer tournamentId, List<PersonGameModel> models,
			List<Game> gameList) {
		
		Person p=personRepository.findByPersonId(personId);
		Tournament t= tournamentRepository.findByTournamentId(tournamentId);
		for (Game game : gameList) {
			PersonGameModel personGameModel=new PersonGameModel();
			models.add(UIModelMapper.update(personGameModel, game));
			
			
			List<PersonGame> personGamelist=personGameRepository.findByTournamentIdAndGameNo(tournamentId,game.getGameNo());
		    updateModelValue(personGamelist,personGameModel);
		    personGameModel.setPersonId(p.getPersonId());
			personGameModel.setPersonTitle(p.getTitle());
			personGameModel.setTournamentId(tournamentId);
			personGameModel.setTournamentTitile(t.getTitle());
		}
		
		return models;
	}

	
	@Override
	public GameDTO findGame(Integer gameNo) {

		Game game = gameRepository.findByGameNo(gameNo);
		GameDTO gameDTO = modelMapper.map(game, GameDTO.class);

		return gameDTO;
	}

	@Override
	public List<GameDTO> findAllGame(Integer tournament) {
		
		
		Iterable<Game> gameEntityList = gameRepository.findByTournament(new Tournament(tournament));
		Type listType = new TypeToken<List<GameDTO>>() {
		}.getType();
		List<GameDTO> gameDTOList = modelMapper.map(gameEntityList, listType);

		return gameDTOList;
	}
	
	@Override
	public List<GameDTO> findAllGameByTeam(Integer tournamentId,Integer teamId,Date FromDate) {

		//Iterable<Game> gameEntityList = gameRepository.findByScheduleAfterAndTournamentAndTeamAIdOrTeamBIdOrderByGameNoAsc(FromDate,new Tournament(tournamentId),teamId,teamId);
		
		Iterable<Game> gameEntityList = gameRepository.findByTournamentAndTeamAIdOrTeamBIdOrderByGameNoAsc(new Tournament(tournamentId),teamId,teamId);
		
		Type listType = new TypeToken<List<GameDTO>>() {
		}.getType();
		List<GameDTO> gameDTOList = modelMapper.map(gameEntityList, listType);
		
		return gameDTOList;
	}
	

	@Override
	public List<TournamentDTO> findTournament(Integer year, boolean active) {
		
		Iterable<Tournament> tournamentList = tournamentRepository.findAll();
		Type listType = new TypeToken<List<TournamentDTO>>() {
		}.getType();
		List<TournamentDTO> tournamentDTOList = modelMapper.map(tournamentList, listType);

		return tournamentDTOList;
	}

	@Override
	public PersonDashboardModel getPersonDashboardInfo(Integer personId) {

		PersonDashboardModel  model=new PersonDashboardModel();
		List<PersonTournament> personTournamentlist=null;
		//PT
		personTournamentlist=personTournamentRepository.findByPersonId(personId);
		if(personTournamentlist!=null && personTournamentlist.size()>0){
			
			PersonTournament	personsTournament=null;
			for (PersonTournament personTournamentTemp : personTournamentlist) {
				if(personTournamentTemp.getTournament().getActive()){
				     personsTournament=personTournamentTemp;
				     break;
				}
			}
			//if rapson can not participate 
			if(personsTournament!=null && personsTournament.getPerson().getParticipate()==false){
				
				UIModelMapper.update(model, personsTournament,null,null);
				return model;
				
			}
			else if(personsTournament!=null && personsTournament.getPerson().getParticipate()==true){
			
				List<PersonGame> list=null;
				
		        Game gameNext = gameRepository.findByGameTournamentSequenceNo(personsTournament.getTournament().getNextGameNo());
		        PersonGame personGameNext=null;
		        if(gameNext!=null){
			        list=personGameRepository.findByPersonIdAndTournamentIdAndGameNo(personId, personsTournament.getTournamentId(),gameNext.getGameNo());
			        if(list!=null&& list.size()>0)
			        	personGameNext=list.get(0);
			        
			        List<PersonGame> personGamelistForAll=personGameRepository.findByTournamentIdAndGameNo(personsTournament.getTournamentId(),gameNext.getGameNo());
			        PersonGameModel personGameModel=new PersonGameModel();
			        updateModelValue(personGamelistForAll,personGameModel);
			        model.setCurrentPersonGameModel(personGameModel);
			        
			        
			        //handle tournament starting when info not available in gameperson table
			        if(personGameNext==null){
			        	UIModelMapper.update(personGameModel,gameNext);
			        	personGameModel.setPersonId(personId);
			        
			        }
			        
		        }
		        
		        Game gameOld = gameRepository.findByGameTournamentSequenceNo(personsTournament.getTournament().getNextGameNo()-1);
		        PersonGame personGameOld=null;
		        if(gameOld!=null){
			        list=personGameRepository.findByPersonIdAndTournamentIdAndGameNo(personId, personsTournament.getTournamentId(),gameOld.getGameNo());
			        if(list!=null&& list.size()>0)
			        	personGameOld=list.get(0);
			        
			        List<PersonGame> personGamelist=personGameRepository.findByTournamentIdAndGameNo(personsTournament.getTournamentId(),gameOld.getGameNo());
			        PersonGameModel personGameModel=new PersonGameModel();
			        updateModelValue(personGamelist,personGameModel);
			        model.setLastPersonGameModel(personGameModel);
			        
			      //handle tournament starting when info not available in gameperson table
			        if(personGameOld==null){
			        	UIModelMapper.update(personGameModel,gameOld);
			        	personGameModel.setPersonId(personId);
			        }
		        }
			
		        /*model.setTotalParticipated(Integer totalParticipated);
				model.setTotalPerson(Integer totalPerson);
				model.setTotalTeamAValue(Double totalTeamAValue);
				model.setTotalTeamBValue(Double totalTeamBValue);*/
		        
		        
		     model.setPersonTitle(findPerson(personId).getTitle());
		     
		     Team team=teamRepository.findOne(personsTournament.getTeamId());
		     if(team!=null)
		     model.setTeamTitle(teamRepository.findOne(personsTournament.getTeamId()).getTitle());  
		     
			UIModelMapper.update(model, personsTournament,personGameNext,personGameOld);
			
			return model;
			}
					
		
		}
		return null;
	}


	
	private PersonGameModel updateModelValue (List<PersonGame> personGamelist, PersonGameModel personGameModel){
		
		Double totalTeamAContribution=new Double(0.0);
		Double totalTeamBContribution=new Double(0.0);
		Integer totalA=0;
		Integer totalB=0;
		
		Integer participatedTotal=0;
		
		for (PersonGame personGame : personGamelist) {
				totalTeamAContribution=totalTeamAContribution+personGame.getTeamAId();
				totalTeamBContribution=totalTeamBContribution+personGame.getTeamBId();
				if(personGame.getTeamAId()!=null && personGame.getTeamAId()>0)
					totalA++;
				
				if(personGame.getTeamBId()!=null && personGame.getTeamBId()>0)
					totalB++;
				
				if(personGame.isParticipated())
					participatedTotal++;
		}
		personGameModel.setTotalTeamAValue(totalTeamAContribution);
		personGameModel.setTotalTeamBValue(totalTeamBContribution);
		personGameModel.setTotalParticipated(participatedTotal);
		
		personGameModel.setTotalTeamB(totalB);
		personGameModel.setTotalTeamA(totalA);
		
		return personGameModel;
	}

	
	@Override
	public List<PersonGameModel> findAllPersonsGame(Integer personId, Integer tournamentId) {
		List<PersonGameModel> models=new ArrayList<PersonGameModel>();
		
		if(personId!=null && tournamentId!=null){
		
			List<PersonGame> list=personGameRepository.findByPersonIdAndTournamentIdOrderByGameNoAsc( personId,tournamentId);
			
			if(list!=null && list.size()>0){
				
					for (PersonGame personGame : list) {
						PersonGameModel personGameModel=new PersonGameModel();
						models.add(UIModelMapper.update(personGameModel, personGame));
						//List<PersonGame> personGamelist=personGameRepository.findByTournamentIdAndGameNo(tournamentId,personGame.getGame().getGameNo());
				        //updateModelValue(personGamelist,personGameModel);
					}
			   }
		  }
		return models;
	    }

	@Override
	public TeamPerformanceDTO findTeamPerformance(Integer tournamentId, Integer teamId) {
		TeamPerformanceDTO teamPerformanceDTO=null;
		if(tournamentId!=null && teamId!=null){
			List<TeamPerformance> teamPerformanceList = (List<TeamPerformance>) teamPerformanceRepository.findByTournamentIdAndTeamId(tournamentId,teamId);
			if(teamPerformanceList!=null && teamPerformanceList.size()>0){
			 teamPerformanceDTO = modelMapper.map(teamPerformanceList.get(0), TeamPerformanceDTO.class);
			 teamPerformanceDTO.setDecs(teamPerformanceList.get(0).getTeam().getDecs());
			 teamPerformanceDTO.setTitle(teamPerformanceList.get(0).getTeam().getTitle());
			}
		}
		
		return teamPerformanceDTO;
	}
	
	@Override
	public List<TeamPerformanceDTO> findAllTeamPerformance(Integer tournamentId) {
	
		
		List<TeamPerformanceDTO> teamPerformanceDTOList=new ArrayList<TeamPerformanceDTO>();
		if(tournamentId!=null){
			List<TeamPerformance> teamPerformanceList =	teamPerformanceRepository.findByTournamentId(tournamentId);
			for (TeamPerformance teamPerformance : teamPerformanceList) {
				teamPerformanceDTOList.add(modelMapper.map(teamPerformance, TeamPerformanceDTO.class));
			}
			
		}
		
		return teamPerformanceDTOList;
		
	}

	
}
