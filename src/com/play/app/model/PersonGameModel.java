package com.play.app.model;

import java.util.Date;

public class PersonGameModel {

	
	private Integer personId;
	private Boolean canParticipate;
	private String personTitle;
	private Integer tournamentId;
	private Integer gameNo;
    private Integer gameTournamentSequenceNo;
	//final,semi final ,group match, 
	private String gametype;
	private Integer personePointMultiplier;
	private String location;
	private Date schedule;
	private Date lockschedule;
	private Boolean overlookLockSchedule;
	private Boolean lockStatus;
	private Integer teamAId;
	private Integer teamBId;
	private String teamATitle;
	private String teamBTitle;
	
	private Integer homeTeamId;
	private Integer winerId;
	//P=Play,C=cancel,D=draw
	private String status;
	
	private Double teamAValue;
	private Double teamBValue;
	private Double points;
	private String result;
	private boolean participated;
	private Integer modifiedCount;
	 
	//calculated 
	private Integer totalParticipated;
	private Integer totalPerson;
	private Double  totalTeamAValue;
	private Double  totalTeamBValue;
	private Integer totalTeamA;
	private Integer totalTeamB;
	
	private String tournamentTitile;
	private Double personPointBaseValue;
	private Integer personPointNextIncrementValue;
	
	//Total point so far for a person
	private Double totalPoints;
	
	public void setGameNo(Integer gameNo) {
		this.gameNo = gameNo;
	}
	public void setGameTournamentSequenceNo(Integer gameTournamentSequenceNo) {
		this.gameTournamentSequenceNo = gameTournamentSequenceNo;
	}
	public void setGametype(String gametype) {
		this.gametype = gametype;
	}
	public void setPersonePointMultiplier(Integer personePointMultiplier) {
		this.personePointMultiplier = personePointMultiplier;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	public void setLockschedule(Date lockschedule) {
		this.lockschedule = lockschedule;
	}
	public void setOverlookLockSchedule(Boolean overlookLockSchedule) {
		this.overlookLockSchedule = overlookLockSchedule;
	}
	public void setLockStatus(Boolean lockStatus) {
		this.lockStatus = lockStatus;
	}
	public void setTeamAId(Integer teamAId) {
		this.teamAId = teamAId;
	}
	public void setTeamBId(Integer teamBId) {
		this.teamBId = teamBId;
	}
	public void setHomeTeamId(Integer homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	public void setWinerId(Integer winerId) {
		this.winerId = winerId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTeamAValue(Double teamAValue) {
		this.teamAValue = teamAValue;
	}
	public void setTeamBValue(Double teamBValue) {
		this.teamBValue = teamBValue;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setParticipated(boolean participated) {
		this.participated = participated;
	}
	public void setModifiedCount(Integer modifiedCount) {
		this.modifiedCount = modifiedCount;
	}
	public void setTotalParticipated(Integer totalParticipated) {
		this.totalParticipated = totalParticipated;
	}
	public void setTotalPerson(Integer totalPerson) {
		this.totalPerson = totalPerson;
	}
	public void setTotalTeamAValue(Double totalTeamAValue) {
		this.totalTeamAValue = totalTeamAValue;
	}
	public void setTotalTeamBValue(Double totalTeamBValue) {
		this.totalTeamBValue = totalTeamBValue;
	}
	public Integer getGameNo() {
		return gameNo;
	}
	public Integer getGameTournamentSequenceNo() {
		return gameTournamentSequenceNo;
	}
	public String getGametype() {
		return gametype;
	}
	public Integer getPersonePointMultiplier() {
		return personePointMultiplier;
	}
	public String getLocation() {
		return location;
	}
	public Date getSchedule() {
		return schedule;
	}
	public Date getLockschedule() {
		return lockschedule;
	}
	public Boolean getOverlookLockSchedule() {
		return overlookLockSchedule;
	}
	public Boolean getLockStatus() {
		return lockStatus;
	}
	public Integer getTeamAId() {
		return teamAId;
	}
	public Integer getTeamBId() {
		return teamBId;
	}
	public Integer getHomeTeamId() {
		return homeTeamId;
	}
	public Integer getWinerId() {
		return winerId;
	}
	public String getStatus() {
		return status;
	}
	public Double getTeamAValue() {
		return teamAValue;
	}
	public Double getTeamBValue() {
		return teamBValue;
	}
	public Double getPoints() {
		return points;
	}
	public String getResult() {
		return result;
	}
	public boolean isParticipated() {
		return participated;
	}
	public Integer getModifiedCount() {
		return modifiedCount;
	}
	public Integer getTotalParticipated() {
		return totalParticipated;
	}
	public Integer getTotalPerson() {
		return totalPerson;
	}
	public Double getTotalTeamAValue() {
		return totalTeamAValue;
	}
	public Double getTotalTeamBValue() {
		return totalTeamBValue;
	}
	public Integer getTotalTeamA() {
		return totalTeamA;
	}
	public void setTotalTeamA(Integer totalTeamA) {
		this.totalTeamA = totalTeamA;
	}
	public Integer getTotalTeamB() {
		return totalTeamB;
	}
	public void setTotalTeamB(Integer totalTeamB) {
		this.totalTeamB = totalTeamB;
	}
	public String getTeamATitle() {
		return teamATitle;
	}
	public void setTeamATitle(String teamATitle) {
		this.teamATitle = teamATitle;
	}
	public String getTeamBTitle() {
		return teamBTitle;
	}
	public void setTeamBTitle(String teamBTitle) {
		this.teamBTitle = teamBTitle;
	}
	public String getTournamentTitile() {
		return tournamentTitile;
	}
	public void setTournamentTitile(String tournamentTitile) {
		this.tournamentTitile = tournamentTitile;
	}
	public Double getPersonPointBaseValue() {
		return personPointBaseValue;
	}
	public void setPersonPointBaseValue(Double personPointBaseValue) {
		this.personPointBaseValue = personPointBaseValue;
	}
	public Integer getPersonPointNextIncrementValue() {
		return personPointNextIncrementValue;
	}
	public void setPersonPointNextIncrementValue(Integer personPointNextIncrementValue) {
		this.personPointNextIncrementValue = personPointNextIncrementValue;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getPersonTitle() {
		return personTitle;
	}
	public void setPersonTitle(String personTitle) {
		this.personTitle = personTitle;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Double getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Double totalPoints) {
		this.totalPoints = totalPoints;
	}
	public Boolean getCanParticipate() {
		return canParticipate;
	}
	public void setCanParticipate(Boolean canParticipate) {
		this.canParticipate = canParticipate;
	}
	

}
