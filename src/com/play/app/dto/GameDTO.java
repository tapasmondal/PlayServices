package com.play.app.dto;

import java.util.Date;

import com.play.app.entity.Team;


public class GameDTO {

	private Integer gameNo;
	
	private Integer gameTournamentSequenceNo;
	
	//final,semi final ,group match, 
	private String type;
	
	private Integer personePointMultiplier;
	
	private String location;
	
	private Date schedule;
	
	private Date lockschedule;
	
	private Boolean overlookLockSchedule;
	
	private Boolean lockStatus;
	
	private Integer teamAId;
	
	private Integer teamBId;
	
	private Integer homeTeamId;
	
	private Integer winerId;
	
	
	//P=Play,C=cancel,D=draw
	private String status;
	
	
	private TournamentDTO tournament;
	
	private TeamDTO teamA; 
	private TeamDTO teamB; 
	
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	public Integer getGameNo() {
		return gameNo;
	}
	public void setGameNo(Integer gameNo) {
		this.gameNo = gameNo;
	}
	public Integer getGameTournamentSequenceNo() {
		return gameTournamentSequenceNo;
	}
	public void setGameTournamentSequenceNo(Integer gameTournamentSequenceNo) {
		this.gameTournamentSequenceNo = gameTournamentSequenceNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPersonePointMultiplier() {
		return personePointMultiplier;
	}
	public void setPersonePointMultiplier(Integer personePointMultiplier) {
		this.personePointMultiplier = personePointMultiplier;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	public Date getLockschedule() {
		return lockschedule;
	}
	public void setLockschedule(Date lockschedule) {
		this.lockschedule = lockschedule;
	}
	public Boolean getOverlookLockSchedule() {
		return overlookLockSchedule;
	}
	public void setOverlookLockSchedule(Boolean overlookLockSchedule) {
		this.overlookLockSchedule = overlookLockSchedule;
	}
	public Boolean getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(Boolean lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Integer getTeamAId() {
		return teamAId;
	}
	public void setTeamAId(Integer teamAId) {
		this.teamAId = teamAId;
	}
	public Integer getTeamBId() {
		return teamBId;
	}
	public void setTeamBId(Integer teamBId) {
		this.teamBId = teamBId;
	}
	public Integer getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(Integer homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	public Integer getWinerId() {
		return winerId;
	}
	public void setWinerId(Integer winerId) {
		this.winerId = winerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TournamentDTO getTournament() {
		return tournament;
	}
	public void setTournament(TournamentDTO tournament) {
		this.tournament = tournament;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public TeamDTO getTeamA() {
		return teamA;
	}
	public void setTeamA(TeamDTO teamA) {
		this.teamA = teamA;
	}
	public TeamDTO getTeamB() {
		return teamB;
	}
	public void setTeamB(TeamDTO teamB) {
		this.teamB = teamB;
	}
	
	
	
	
}
