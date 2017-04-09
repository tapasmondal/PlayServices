package com.play.app.model;

import com.play.app.entity.Game;
import com.play.app.entity.PersonGame;
import com.play.app.entity.PersonTournament;

public  abstract class UIModelMapper {

	public static PersonDashboardModel update(PersonDashboardModel model, PersonTournament pt, PersonGame personGameNext, PersonGame personGameOld){
		
	/*	private Integer personId;
		private String  personTitle;
		private Integer tournamentId;
		private String tournamentTitle;
		private Integer teamId;
		private String teamTitle;
		private Double  totalPoints;
		private Integer ranking;
		private Integer gamePlayed;
		private Integer gameLeft;
		private Integer win;
		private Integer loss;
		private Integer draw;*/
		
		if(model!=null && pt!=null){
			model.setPersonId(pt.getPersonId()) ;
			model.setTournamentId(pt.getTournamentId());
			model.setTournamentTitle(pt.getTournament().getTitle());
			model.setTeamId(pt.getTeamId());
			model.setTotalPoints(pt.getTotalPoints());
			model.setRanking(pt.getRanking());
			model.setGamePlayed(pt.getGamePlayed());
			model.setGameLeft(pt.getGameLeft());
			model.setWin(pt.getWin());
			model.setLoss(pt.getLoss());
			model.setDraw(pt.getDraw());
			
			if(pt.getPerson()!=null){
				model.setParticipate(pt.getPerson().getParticipate());
				model.setBalance(pt.getPerson().getBalance());
			}
			
			if(model.getCurrentPersonGameModel()!=null && personGameNext!=null )
			update(model.getCurrentPersonGameModel(),personGameNext);
			
			if(model.getLastPersonGameModel()!=null && personGameOld!=null)
			update(model.getLastPersonGameModel(),personGameOld);

		}
		
		return model;
	}
	
			
	public static PersonGameModel update(PersonGameModel model,PersonGame personGame){
		
	
		if(model!=null &&personGame!=null){
			
			
		model.setPersonId(personGame.getPersonId());	
		model.setPersonTitle(personGame.getPerson().getTitle());
		model.setCanParticipate(personGame.getPerson().getParticipate());
		model.setTournamentId(personGame.getTournamentId());
		model.setGameNo(personGame.getGame().getGameNo());
		model.setGameTournamentSequenceNo(personGame.getGame().getGameTournamentSequenceNo());
		model.setGametype(personGame.getGame().getType());
		model.setPersonePointMultiplier(personGame.getGame().getPersonePointMultiplier());
		model.setLocation(personGame.getGame().getLocation());
		model.setSchedule(personGame.getGame().getSchedule());
		model.setLockschedule(personGame.getGame().getLockschedule());
		model.setOverlookLockSchedule(personGame.getGame().getOverlookLockSchedule());
		model.setLockStatus(personGame.getGame().getLockStatus());
		model.setTeamAId(personGame.getGame().getTeamAId());
		model.setTeamBId(personGame.getGame().getTeamBId());
		model.setHomeTeamId(personGame.getGame().getHomeTeamId());
		model.setWinerId(personGame.getGame().getWinerId());
		model.setStatus(personGame.getGame().getStatus());
		model.setTeamAValue(personGame.getTeamAId());
		model.setTeamBValue(personGame.getTeamBId());
		model.setPoints(personGame.getPoints());
		model.setResult(personGame.getResult());
		model.setParticipated(personGame.isParticipated());
		model.setModifiedCount(personGame.getModifiedCount());
		model.setTotalPoints(personGame.getTotalPoints());
		
		model.setTeamBTitle(personGame.getGame().getTeamB().getTitle());
		model.setTeamATitle(personGame.getGame().getTeamA().getTitle());
		
		model.setTournamentTitile(personGame.getGame().getTournament().getTitle());
		model.setPersonPointNextIncrementValue(personGame.getGame().getTournament().getPersonPointNextIncrementValue());
		model.setPersonPointBaseValue(personGame.getGame().getTournament().getPersonPointBaseValue());
		
		
		
		}
		
		return model;
		
	}


	public static PersonGameModel update(PersonGameModel model, Game game) {
		
		if(model!=null && game!=null){
		model.setGameNo(game.getGameNo());
		model.setGameTournamentSequenceNo(game.getGameTournamentSequenceNo());
		model.setGametype(game.getType());
		model.setPersonePointMultiplier(game.getPersonePointMultiplier());
		model.setLocation(game.getLocation());
		model.setSchedule(game.getSchedule());
		model.setLockschedule(game.getLockschedule());
		model.setOverlookLockSchedule(game.getOverlookLockSchedule());
		model.setLockStatus(game.getLockStatus());
		model.setTeamAId(game.getTeamAId());
		model.setTeamBId(game.getTeamBId());
		model.setHomeTeamId(game.getHomeTeamId());
		model.setWinerId(game.getWinerId());
		model.setStatus(game.getStatus());
		model.setParticipated(false);
		model.setTeamBTitle(game.getTeamB().getTitle());
		model.setTeamATitle(game.getTeamA().getTitle());
		
		model.setTournamentId(game.getTournament().getTournamentId());
		model.setTournamentTitile(game.getTournament().getTitle());
		model.setPersonPointNextIncrementValue(game.getTournament().getPersonPointNextIncrementValue());
		model.setPersonPointBaseValue(game.getTournament().getPersonPointBaseValue());
		
		}
		return model;
	}
}
