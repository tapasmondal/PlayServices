package com.play.app.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.play.app.constant.PlayAppConstant;
import com.play.app.entity.Game;
import com.play.app.entity.Person;
import com.play.app.entity.PersonGame;
import com.play.app.entity.PersonGameTemp;
import com.play.app.entity.PersonHistory;
import com.play.app.entity.PersonTournament;
import com.play.app.entity.TeamPerformance;
import com.play.app.entity.Tournament;
import com.play.app.model.AdminUpdateModel;
import com.play.app.model.PersonModel;
import com.play.app.model.VoteInfoModel;
import com.play.app.repositories.GameRepository;
import com.play.app.repositories.PersonGameRepository;
import com.play.app.repositories.PersonGameRepositoryTemp;
import com.play.app.repositories.PersonHistoryRepository;
import com.play.app.repositories.PersonRepository;
import com.play.app.repositories.PersonTournamentRepository;
import com.play.app.repositories.TeamPerformanceRepository;
import com.play.app.repositories.TournamentRepository;
import com.play.app.service.PlayUpdateService;



@Service("PlayUpdateService")
@Transactional
public class PlayUpdateServiceImpl implements PlayUpdateService {

	
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
	private TeamPerformanceRepository teamPerformanceRepository;
	
	@Autowired
	private PersonGameRepositoryTemp personGameRepositoryTemp;
	
	@Autowired
	private PersonHistoryRepository personHistoryRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	
	public PersonModel updatePersonDataByAdmin(PersonModel personModel){
		
		try{
		if(personModel!=null && personModel.getPersonId()!=null){
			
		  Person person = personRepository.findByPersonId(personModel.getPersonId());
		 
		  if(personModel.getParticipate()!=null){
			   boolean oldStatus=person.getParticipate();
			   Double oldBalance=person.getBalance();
			   
			   
			   person.setParticipate(personModel.getParticipate());
			   person.setLastModifiedBy("Sys Admin");
			   Calendar calendar = new GregorianCalendar();
			   person.setLastModifiedDate(calendar.getTime());
			   
			   if(personModel.getBalance()!=null){
			    person.setBalance(person.getBalance()!=null?person.getBalance()+personModel.getBalance():personModel.getBalance());
			   }
			   personRepository.save(person);
			   //audit log data 
			   updatePersonHistory(person,oldBalance,oldStatus,"Admin");
			   
			   personModel.setStatus(true);
			   personModel.setMessage("Status updated !");
			   return personModel;
		  }
		}
		}
		catch(Throwable t){
			 t.printStackTrace();
		}
		personModel.setStatus(true);
		personModel.setMessage("Unable to updated !");
		return personModel;
	}
	
	
	private void updatePersonHistory(Person person,Double oldBalance,boolean oldStatus,String modifiedby){
		
		try{	
			PersonHistory personHistory= new PersonHistory();
			personHistory.setCreatedBy(modifiedby);
			Calendar calendar = new GregorianCalendar();
			personHistory.setCreatedDate(calendar.getTime());
			personHistory.setNewBalance(person.getBalance());
			personHistory.setNewParticipateStatus(person.getParticipate());
			personHistory.setOldBalance(oldBalance);
			personHistory.setOldParticipateStatus(oldStatus);
			personHistory.setTitle(person.getTitle());
			personHistory.setPersonId(person.getPersonId());
			personHistoryRepository.save(personHistory);
			
		}
		catch(Throwable t){
			 t.printStackTrace();
		}
		
	}
	
	@Override
	public VoteInfoModel updatePersonsGameByPerson(VoteInfoModel voteInfoModel) {
     
		try{
			if(voteInfoModel.getPersonId()!=null && voteInfoModel.getGameNo()!=null && voteInfoModel.getTournamentId()!=null){
				
			Person person = personRepository.findByPersonId(voteInfoModel.getPersonId());
			
			Double teamAValue=voteInfoModel.getTeamAValue();
			Double teamBValue=voteInfoModel.getTeamBValue();
			Double total=0.0;
			
			if(teamAValue!=null && teamBValue!=null){
				
				total=teamAValue+teamBValue;
			}
			else if(teamAValue!=null && teamBValue==null){
				total=teamAValue;
			}
			else if(teamBValue!=null && teamAValue==null){
				total=teamBValue;
			}
			
			if(person.getParticipate()==false){
				 voteInfoModel.setUpdatedStatus(false);
				 voteInfoModel.setMessage("Unable to serve you, contact Admin !");
				 return voteInfoModel;
			}
			else if(total.doubleValue()>person.getBalance()){
					voteInfoModel.setUpdatedStatus(false);
					voteInfoModel.setMessage("Can not update as balance Value is less than Vote , contact Admin !");
					return voteInfoModel;
			}
			
			Game g= gameRepository.findByGameNo( voteInfoModel.getGameNo());
			
			if(g!=null){
				if(g.getLockStatus()){
					 voteInfoModel.setUpdatedStatus(false);
					 voteInfoModel.setMessage("Game is locked, Unable to Update !");
					 return voteInfoModel;
				}
			}
			PersonGame personGame=null;
			List<PersonGame> listlist=personGameRepository.findByPersonIdAndTournamentIdAndGameNo(voteInfoModel.getPersonId(), voteInfoModel.getTournamentId(),voteInfoModel.getGameNo());
			
				if(listlist!=null && !listlist.isEmpty()){
					personGame=listlist.get(0);
				}
			
			
			//This part of the code to be cleaned later
			//TODO
			if(personGame!=null){
				personGame.setGame(new Game(voteInfoModel.getGameNo()));
				personGame.setTeamAId((voteInfoModel.getTeamAValue()==null||voteInfoModel.getTeamAValue().doubleValue()<0)?new Double(0.0):voteInfoModel.getTeamAValue());
				personGame.setTeamBId((voteInfoModel.getTeamBValue()==null||voteInfoModel.getTeamBValue().doubleValue()<0)?new Double(0.0):voteInfoModel.getTeamBValue());
				personGame.setLastModifiedBy(person.getTitle());
				
				
				if((voteInfoModel.getTeamAValue()!=null && voteInfoModel.getTeamAValue().doubleValue()>0)
						||
				  (voteInfoModel.getTeamBValue()!=null && voteInfoModel.getTeamBValue().doubleValue()>0)
				)
				personGame.setParticipated(true);
				else
					personGame.setParticipated(false);
				
				
				if(personGame.isParticipated()){
					
					personGame.setModifiedCount(personGame.getModifiedCount()+1);
					voteInfoModel.setModifiedcounter(personGame.getModifiedCount());
				}
				
				personGame.setLastModifiedDate(voteInfoModel.getVotingTime());
				personGame=personGameRepository.save(personGame);
			}
			else{
				//data has to be populated from  from service 
				personGame=new PersonGame();
				personGame.setGame(new Game(voteInfoModel.getGameNo()));
				personGame.setGameNo(voteInfoModel.getGameNo());
				personGame.setTournamentId(voteInfoModel.getTournamentId());
				personGame.setTournament(new Tournament(voteInfoModel.getTournamentId()));
				personGame.setPerson(new Person(voteInfoModel.getPersonId()));
				personGame.setPersonId(voteInfoModel.getPersonId());
				personGame.setModifiedCount(0);
				if((voteInfoModel.getTeamAValue()!=null && voteInfoModel.getTeamAValue().doubleValue()>0)
						||
				  (voteInfoModel.getTeamBValue()!=null && voteInfoModel.getTeamBValue().doubleValue()>0)
				)
				personGame.setParticipated(true);
				else
					personGame.setParticipated(false);
				
				//personGame.setPersonId(voteInfoModel.getPersonId());
				
				personGame.setTeamAId((voteInfoModel.getTeamAValue()==null||voteInfoModel.getTeamAValue().doubleValue()<0)?new Double(0.0):voteInfoModel.getTeamAValue());
				personGame.setTeamBId((voteInfoModel.getTeamBValue()==null||voteInfoModel.getTeamBValue().doubleValue()<0)?new Double(0.0):voteInfoModel.getTeamBValue());
				personGame.setPoints(new Double(0.0));
				personGame.setCreatedBy(person.getTitle());
				personGame.setLastModifiedBy(person.getTitle());
				personGame.setCreatedDate(voteInfoModel.getVotingTime());
				personGame.setLastModifiedDate(voteInfoModel.getVotingTime());
				//personGame=personGameRepository.save(personGame);
				
				PersonGameTemp personGameTemp = modelMapper.map(personGame, PersonGameTemp.class);
				personGameRepositoryTemp.save(personGameTemp);
			
				
			}
			
			 voteInfoModel.setUpdatedStatus(true);
			 voteInfoModel.setMessage("Vote Updated");
			}
			
		}
		catch(Throwable t){
			
			 t.printStackTrace();
			 voteInfoModel.setUpdatedStatus(false);
			 voteInfoModel.setMessage("Unable to Update !");
			
		}
		return voteInfoModel;
	}

	
	//unlocking and locking 
	@Override
	public AdminUpdateModel updateGameByAdmin(AdminUpdateModel adminUpdateModel) {
		
		try{
			if(adminUpdateModel!=null && adminUpdateModel.getGameNo()!=null){
				
			Game game = gameRepository.findByGameNo(adminUpdateModel.getGameNo());
			
				if(game!=null){
					game.setLockStatus(adminUpdateModel.isGameLockStatus());
					game.setOverlookLockSchedule(adminUpdateModel.isGameLockStatus());
					
					if(adminUpdateModel.getGameLockDelayBy()!=null && adminUpdateModel.getGameLockDelayBy().intValue()>0){
						Calendar calendar = new GregorianCalendar();
				        calendar.setTime(game.getLockschedule());
				        calendar.add(Calendar.MINUTE, adminUpdateModel.getGameLockDelayBy().intValue());
				        game.setLockschedule(calendar.getTime());
					}
					game=gameRepository.save(game);
					
					
					adminUpdateModel.setUpdatedStatus(true);
					adminUpdateModel.setMessage("Game status updated");
					
					//Need to check scheduler need to run
					 //or ask user to again login or in U data needs to be refresh
				}
				else{
					adminUpdateModel.setUpdatedStatus(false);
					 adminUpdateModel.setMessage("Unable to Update !");
				}
			
			}
		
		}
		catch(Throwable t){
			
			 t.printStackTrace();
			 adminUpdateModel.setUpdatedStatus(false);
			 adminUpdateModel.setMessage("Unable to Update !");
			
		}
		
		
		
		return adminUpdateModel;
	}
	
	
	
	
	
	public void updatedGamePersonforNonParticipation(AdminUpdateModel adminUpdateModel){
		
		if(adminUpdateModel!=null && adminUpdateModel.getGameNo()!=null && adminUpdateModel.getTournamentId()!=null){
			Game game = gameRepository.findByGameNo(adminUpdateModel.getGameNo());
			List<PersonGameTemp> personGameTempList=new ArrayList<PersonGameTemp>();
			List<PersonGame> personGameInserstList=new ArrayList<PersonGame>();
			
			if(game!=null){
				
				List<PersonTournament> ptList=personTournamentRepository.findByTournamentId(adminUpdateModel.getTournamentId());
				List<PersonGame> pglist=personGameRepository.findByTournamentIdAndGameNo(adminUpdateModel.getTournamentId(),adminUpdateModel.getGameNo());
				Double defaultContribution= game.getTournament().getPersonPointBaseValue()*game.getPersonePointMultiplier();
				
				boolean recordFoundInpersonGameTable=false;
				
				for (PersonTournament personTournament : ptList) {
					
					//skip the record where person can not participate
					if(personTournament.getPerson().getParticipate()==false){
						continue;
					}
					
					recordFoundInpersonGameTable=false;
					
					
					for (PersonGame personGame : pglist) {
						//record found
						if(personTournament.getPersonId().equals(personGame.getPersonId())){
							recordFoundInpersonGameTable=true;
							//check if participated or not
							if(personGame.isParticipated()==false){
								
								//add home team validation with team A 
								if(personTournament.getTeamId().equals(game.getTeamAId())){
									
									personGame.setTeamAId(defaultContribution);
									personGame.setTeamBId(new Double(0));
									personGame.setParticipated(true);
								}
								//add home team validation with team B
								else if( personTournament.getTeamId().equals(game.getTeamBId())){
									
									personGame.setTeamBId(defaultContribution);
									personGame.setTeamAId(new Double(0));
									personGame.setParticipated(true);
								}
								else {
									//Default value on the loosing team 
									if(adminUpdateModel.getGameWinnerId().equals(game.getTeamAId())){
									 personGame.setTeamBId(defaultContribution);
									 personGame.setTeamAId(new Double(0));
									 personGame.setParticipated(true);
									}
									else
									if( adminUpdateModel.getGameWinnerId().equals(game.getTeamBId())){
										personGame.setTeamAId(defaultContribution);
										personGame.setTeamBId(new Double(0));
										personGame.setParticipated(true);
									}
								}
								
							}
							break;
						}
					}
					
					//if record not found then insert with default values
					if(recordFoundInpersonGameTable==false){
						
						PersonGame personGame=new PersonGame();
						personGame.setGame(new Game(adminUpdateModel.getGameNo()));
						personGame.setGameNo(adminUpdateModel.getGameNo());
						personGame.setTournamentId(personTournament.getTournamentId());
						personGame.setTournament(new Tournament(personTournament.getTournamentId()));
						personGame.setPerson(new Person(personTournament.getPersonId()));
						personGame.setPersonId(personTournament.getPersonId());
						personGame.setModifiedCount(0);
						personGame.setParticipated(true);
						
						//add home team validation with team A 
						if(personTournament.getTeamId().equals(game.getTeamAId())){
							
							personGame.setTeamAId(defaultContribution);
							personGame.setTeamBId(new Double(0));
						}
						//add home team validation with team B
						else if( personTournament.getTeamId().equals(game.getTeamBId())){
							
							personGame.setTeamBId(defaultContribution);
							personGame.setTeamAId(new Double(0));
						}
						else{
							//Default value on the loosing team
							//set default value
							if(adminUpdateModel.getGameWinnerId().equals(game.getTeamAId())){
								personGame.setTeamBId(defaultContribution);
								personGame.setTeamAId(new Double(0));
							}
							else
							if( adminUpdateModel.getGameWinnerId().equals(game.getTeamBId())){
								personGame.setTeamAId(defaultContribution);
								personGame.setTeamBId(new Double(0));
							}
						}
						
						personGame.setPoints(new Double(0));
						personGame.setTotalPoints(new Double(0));
						personGame.setCreatedBy("System-Cal");
						personGame.setLastModifiedBy("System-Cal");
						Calendar calendar = new GregorianCalendar();
						personGame.setCreatedDate(calendar.getTime());
						personGame.setLastModifiedDate(calendar.getTime());
						
						personGameInserstList.add(personGame);
						
					}
				}
				
				//updated
				for (PersonGame personGame : pglist) {
					personGameTempList.add(modelMapper.map(personGame, PersonGameTemp.class));
				}
				personGameRepositoryTemp.save(personGameTempList);
				
				//insert
				personGameTempList=new ArrayList<PersonGameTemp>();
				for (PersonGame personGame : personGameInserstList) {
					personGameTempList.add(modelMapper.map(personGame, PersonGameTemp.class));
				}
				personGameRepositoryTemp.save(personGameTempList);
				
			}
			
		}
	}

	@Override
	public AdminUpdateModel updateGameStatusByAdmin(AdminUpdateModel adminUpdateModel) {
		
		try{
			Calendar calendar = new GregorianCalendar();
		if(adminUpdateModel!=null && adminUpdateModel.getGameNo()!=null){
			
			Game game = gameRepository.findByGameNo(adminUpdateModel.getGameNo());
			if(game==null){
				
				adminUpdateModel.setUpdatedStatus(false);
				adminUpdateModel.setMessage("Unable to Update !");
				return adminUpdateModel;
			}
			
			//updated Game
			game.setLockStatus(true);
			game.setStatus(adminUpdateModel.getGameStatus());
			game.setWinerId(adminUpdateModel.getGameWinnerId());
			game=gameRepository.save(game);
			
			//Update Tournament
			Tournament t=tournamentRepository.findByTournamentId(game.getTournament().getTournamentId());
			t.setGamePlayed(t.getGamePlayed()+1);
			t.setNextGameNo(t.getNextGameNo()+1);
			t=tournamentRepository.save(t);
			
			
			//PersoneGame
			List<PersonGame> personGamelist=personGameRepository.findByTournamentIdAndGameNo(game.getTournament().getTournamentId(),adminUpdateModel.getGameNo());
			List<PersonGameTemp> personGameTempList=new ArrayList<PersonGameTemp>();
			if(personGamelist!=null){
			//if game is not cancel or draw
					if(!game.getStatus().equals(PlayAppConstant.CANCEL) || !game.getStatus().equals(PlayAppConstant.DRAW))
					{
						Double totalTeamAContribution=new Double(0.0);
						Double totalTeamBContribution=new Double(0.0);
						
						for (PersonGame personGame : personGamelist) {
							
							//logic is shifted to other method
							/*if(personGame.isParticipated()==false){
								Double defaultContribution= t.getPersonPointBaseValue()*game.getPersonePointMultiplier();
							
								if(game.getWinerId().intValue()==game.getTeamAId().intValue()){
									personGame.setTeamBId(defaultContribution);
								}
								else
								if( game.getWinerId().intValue()==game.getTeamBId().intValue()){
									personGame.setTeamAId(defaultContribution);
			
								}
							}*/
							    if(personGame.getTeamAId()!=null && personGame.getTeamAId().doubleValue()>0)
								totalTeamAContribution=totalTeamAContribution+personGame.getTeamAId();
							    
							    if(personGame.getTeamBId()!=null && personGame.getTeamBId().doubleValue()>0)
								totalTeamBContribution=totalTeamBContribution+personGame.getTeamBId();
							
						}
						
						//calculate ratio  
						Double ratio=new Double(0.0);
						if(game.getWinerId().intValue()==game.getTeamAId().intValue()){
							
							if(totalTeamAContribution.doubleValue()>0)
								ratio=totalTeamBContribution/totalTeamAContribution;
							else
								ratio=0.0;
									
						}else if(game.getWinerId().intValue()==game.getTeamBId().intValue()){
							
							if(totalTeamBContribution.doubleValue()>0)
							ratio=totalTeamAContribution/totalTeamBContribution;
							else
								ratio=0.0;
						}
						
						//calculate distribute points
						for (PersonGame personGame : personGamelist) {
							
							if(game.getWinerId().intValue()==game.getTeamAId().intValue()){
								personGame.setPoints(ratio.doubleValue()>0? (ratio*personGame.getTeamAId())-personGame.getTeamBId():new Double(0));
								
								if(personGame.getTeamAId().doubleValue()>0 && personGame.getTeamAId().doubleValue()> personGame.getTeamBId().doubleValue())
									personGame.setResult(PlayAppConstant.WIN);
								else
									personGame.setResult(PlayAppConstant.LOSS);
							}
							else
							if(game.getWinerId().intValue()==game.getTeamBId().intValue()){
								personGame.setPoints(ratio.doubleValue()>0?(ratio *personGame.getTeamBId())-personGame.getTeamAId():new Double(0));
								
								if(personGame.getTeamBId().doubleValue()>0 && personGame.getTeamBId().doubleValue()>personGame.getTeamAId().doubleValue())
									personGame.setResult(PlayAppConstant.WIN);
								else
									personGame.setResult(PlayAppConstant.LOSS);
							}
							
							personGame.setLastModifiedBy("System-Cal");
							personGame.setLastModifiedDate(calendar.getTime());
						}
						
					}else{
							//just update result 0.0
							for (PersonGame personGame : personGamelist) {
								personGame.setPoints(new Double(0));
								personGame.setResult(PlayAppConstant.NONE);
								personGame.setLastModifiedBy("System-Cal");
								personGame.setLastModifiedDate(calendar.getTime());
							 }
							
							
						}
					//personGamelist=(List<PersonGame>) personGameRepository.save(personGamelist);
					
					//workaround
					
					for (PersonGame personGame : personGamelist) {
						personGameTempList.add(modelMapper.map(personGame, PersonGameTemp.class));
					}
				
					personGameTempList=(List<PersonGameTemp>)personGameRepositoryTemp.save(personGameTempList);
					
				}
			
			
			
			//update PT
			List<PersonTournament> personTournamentList=personTournamentRepository.findByTournamentId(t.getTournamentId());
			
			if(personTournamentList!=null){
				for (PersonTournament personTournament : personTournamentList) {
					
					//skip the record where person can not participate
					if(personTournament.getPerson().getParticipate()==false){
						continue;
					}
					
					for (PersonGameTemp personGame : personGameTempList) {
						
						if(personGame.getPersonId().equals(personTournament.getPersonId())){
							
							personTournament.setLastModifiedBy("System-Cal");
							personTournament.setLastModifiedDate(calendar.getTime());
							personTournament.setTotalPoints((personTournament.getTotalPoints().isNaN()?new Double(0):personTournament.getTotalPoints())+personGame.getPoints());
							personGame.setTotalPoints(personTournament.getTotalPoints());
							
							Person person = personRepository.findByPersonId(personGame.getPersonId());
							Double oldValue=person.getBalance().doubleValue();
							//update person balance
							person.setBalance(person.getBalance()+personGame.getPoints());
							person.setLastModifiedBy("System-Cal");
							person.setLastModifiedDate(calendar.getTime());
							personRepository.save(person);
							updatePersonHistory(person,oldValue,person.getParticipate(),"System-Cal");
							
							
							personTournament.setGamePlayed(personTournament.getGamePlayed()+1);
							personTournament.setGameLeft(personTournament.getGameLeft()-1);
							
							if(personGame.getResult().equals(PlayAppConstant.LOSS)){
								personTournament.setLoss(personTournament.getLoss()+1);
							}
							else if(personGame.getResult().equals(PlayAppConstant.WIN)){
							personTournament.setWin(personTournament.getWin()+1);
							}
							else if(personGame.getResult().equals(PlayAppConstant.NONE)){
								if(game.getStatus().equals(PlayAppConstant.DRAW))
									personTournament.setDraw(personTournament.getDraw()+1);
								
								if(game.getStatus().equals(PlayAppConstant.CANCEL))
								personTournament.setCancelled(personTournament.getCancelled()+1);
							}
						}
					}
					
				}
				
				//Ranking 
				Collections.sort(personTournamentList);
				int i=1;
				for (PersonTournament personTournament : personTournamentList) {
					personTournament.setRanking(i);
					i++;
				}
				
				personTournamentRepository.save(personTournamentList);
				personGameRepositoryTemp.save(personGameTempList);
			}
			
			//update TP
			
			List<TeamPerformance> teamPerformanceteamList=teamPerformanceRepository.findByTournamentId(t.getTournamentId());
			
			if(teamPerformanceteamList!=null){
				for (TeamPerformance teamPerformance : teamPerformanceteamList) {
					if(teamPerformance.getTeamId().intValue()==game.getTeamAId().intValue() ||teamPerformance.getTeamId().intValue()==game.getTeamBId().intValue()){
						
						if(game.getStatus().equals(PlayAppConstant.COMPLETED)){
							if(teamPerformance.getTeamId().intValue()==game.getWinerId().intValue()){
								teamPerformance.setWin(teamPerformance.getWin()+1);
								teamPerformance.setPoints(teamPerformance.getPoints()+t.getTeamPointBaseValue());
							}else{
								teamPerformance.setLoss(teamPerformance.getLoss()+1);
							}
							teamPerformance.setPlyed(teamPerformance.getPlyed()+1);
						}
						else if(game.getStatus().equals(PlayAppConstant.DRAW)){
							teamPerformance.setDraw(teamPerformance.getDraw()+1);
							teamPerformance.setPlyed(teamPerformance.getPlyed()+1);
							teamPerformance.setPoints(teamPerformance.getPoints()+(t.getTeamPointBaseValue()/2));
						}
						else if(game.getStatus().equals(PlayAppConstant.CANCEL)){
							teamPerformance.setCancel(teamPerformance.getCancel()+1);
							teamPerformance.setPoints(teamPerformance.getPoints()+(t.getTeamPointBaseValue()/2));
						}
						
						teamPerformance.setLastModifiedBy("System-Cal");
						teamPerformance.setLastModifiedDate(calendar.getTime());
					}
				}
				
				teamPerformanceRepository.save(teamPerformanceteamList);
			}
			
				adminUpdateModel.setUpdatedStatus(true);
				adminUpdateModel.setMessage("Updated Game Status ");
				//enroll home team points for next game 
				//autoEnrollNextGameForHomeTeam(adminUpdateModel.getTournamentId(),adminUpdateModel.getGameNo()+1);
				
				return adminUpdateModel;
			}
			else{
			adminUpdateModel.setUpdatedStatus(false);
			adminUpdateModel.setMessage("Unable to Update !");
			}
		}
		catch(Throwable t){
			
			 t.printStackTrace();
			 adminUpdateModel.setUpdatedStatus(false);
			 adminUpdateModel.setMessage("Unable to Update !");
			
		}	  
	
		
		return adminUpdateModel;

		
	}


	@Override
	public void autoLockingGame() {
		
		try{
		
		Date currentTime=new Date();
		Calendar calendar = new GregorianCalendar();
		//calendar.setTime(currentTime);
		
		Date startDate=new DateTime()
				.withYear(calendar.get(Calendar.YEAR))
				.withMonthOfYear(calendar.get(Calendar.MONTH)+1)
				.withDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH))
			    .withHourOfDay(0)
			    .withMinuteOfHour(0)
			    .withSecondOfMinute(1).toDate(); 
		

		
		Date endDate =new DateTime()
				.withYear(calendar.get(Calendar.YEAR))
				.withMonthOfYear(calendar.get(Calendar.MONTH)+1)
				.withDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH))
			    .withHourOfDay(23)
			    .withMinuteOfHour(59)
			    .withSecondOfMinute(59).toDate(); 
		
		System.out.println("PlayUpdateServiceImpl.autoLockingGame() startDate:"+startDate +"endDate :"+endDate);
		
		List<Game> gameList = gameRepository.findByScheduleBetween(startDate, endDate);
		
		boolean update=false;
		//current time in IST
		
		System.out.println("PlayUpdateServiceImpl.autoLockingGame() currentTime:"+currentTime );
		
		for (Game game : gameList) {
			
			 if( currentTime.after(game.getLockschedule()) && game.getLockStatus()==false){
				 System.out.println("PlayUpdateServiceImpl.autoLockingGame() : locking game no:"+game.getGameNo());
				 
				 game.setLockStatus(true);
			 	 game.setOverlookLockSchedule(true);
			 	 update=true;
			 	 
			 	 autoEnrollNextGameForHomeTeam(game.getTournament().getTournamentId(),game.getGameNo());
			 }
			
		  }
			if(update)
			gameRepository.save(gameList);
		}
		catch(Throwable t){
			System.out.println("PlayUpdateServiceImpl.autoLockingGame():-START------------ERRROR-----------------------");
			t.printStackTrace();
			System.out.println("PlayUpdateServiceImpl.autoLockingGame():-END------------ERRROR-----------------------");
			
		}
		
	}

	private void autoEnrollNextGameForHomeTeam(Integer tournamentId, Integer gameNo){
		try{
		if(gameNo!=null && tournamentId!=null){
			Game game = gameRepository.findByGameNo(gameNo);
			List<PersonGameTemp> personGameTempList=new ArrayList<PersonGameTemp>();
			List<PersonGame> personGameInserstList=new ArrayList<PersonGame>();
			
			if(game!=null){
				
				List<PersonTournament> ptList=personTournamentRepository.findByTournamentId(tournamentId);
				List<PersonGame> pglist=personGameRepository.findByTournamentIdAndGameNo(tournamentId,gameNo);
				Double defaultContribution= game.getTournament().getPersonPointBaseValue()*game.getPersonePointMultiplier();
				
				boolean recordFoundInpersonGameTable=false;
				
				for (PersonTournament personTournament : ptList) {
					
					//skip the record where person can not participate
					if(personTournament.getPerson().getParticipate()==false){
						continue;
					}
					
					recordFoundInpersonGameTable=false;
					
					
					for (PersonGame personGame : pglist) {
						//record found
						if(personTournament.getPersonId().equals(personGame.getPersonId())){
							recordFoundInpersonGameTable=true;
							//check if participated or not
							break;
						}
					}
					
					//if enroll points if home team 
					if(recordFoundInpersonGameTable==false &&
							(personTournament.getTeamId().equals(game.getTeamAId())
									|| personTournament.getTeamId().equals(game.getTeamBId()))){
						
						PersonGame personGame=new PersonGame();
						personGame.setGame(new Game(gameNo));
						personGame.setGameNo(gameNo);
						personGame.setTournamentId(personTournament.getTournamentId());
						personGame.setTournament(new Tournament(personTournament.getTournamentId()));
						personGame.setPerson(new Person(personTournament.getPersonId()));
						personGame.setPersonId(personTournament.getPersonId());
						personGame.setModifiedCount(0);
						personGame.setParticipated(true);
						
						//add home team validation with team A 
						if(personTournament.getTeamId().equals(game.getTeamAId())){
							
							personGame.setTeamAId(defaultContribution);
							personGame.setTeamBId(new Double(0));
						}
						//add home team validation with team B
						else if( personTournament.getTeamId().equals(game.getTeamBId())){
							
							personGame.setTeamBId(defaultContribution);
							personGame.setTeamAId(new Double(0));
						}
						
						
						personGame.setPoints(new Double(0));
						personGame.setTotalPoints(new Double(0));
						personGame.setCreatedBy("System - Enrolled");
						personGame.setLastModifiedBy("System - Enrolled");
						Calendar calendar = new GregorianCalendar();
						personGame.setCreatedDate(calendar.getTime());
						personGame.setLastModifiedDate(calendar.getTime());
						
						personGameInserstList.add(personGame);
						
					}
				}
				
				
				//insert points for home team 
				personGameTempList=new ArrayList<PersonGameTemp>();
				for (PersonGame personGame : personGameInserstList) {
					personGameTempList.add(modelMapper.map(personGame, PersonGameTemp.class));
				}
				personGameRepositoryTemp.save(personGameTempList);
				
			}//game !=null
			
		}
		
		}catch(Throwable t){
			System.out.println("PlayUpdateServiceImpl.autoEnrollForHomeTeam():-START------------ERRROR-----------------------");
			t.printStackTrace();
			System.out.println("PlayUpdateServiceImpl.autoEnrollForHomeTeam():-START------------ERRROR-----------------------");
		}
	}
	

	
}
