package com.play.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Game{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer gameNo;
	
	@Column(nullable = false)
	private Integer gameTournamentSequenceNo;
	
	
	//final,semi final ,group match, 
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Integer personePointMultiplier;
	
	@Column(length = 100,nullable = false)
	private String location;
	
	@Column(nullable = false)
	private Date schedule;
	
	@Column(nullable = false)
	private Date lockschedule;
	
	@Column(nullable = false)
	private Boolean overlookLockSchedule;
	
	@Column(nullable = false)
	private Boolean lockStatus;
	
	@Column(nullable = false,insertable = false, updatable = false)
	private Integer teamAId;
	
	@Column(nullable = false,insertable = false, updatable = false)
	private Integer teamBId;
	
	@Column
	private Integer homeTeamId;
	
	@Column
	private Integer winerId;
	
	
	//P=Play,C=cancel,D=draw
	@Column
	private String status;
	
	
	@ManyToOne
	@JoinColumn(name="tournamentId")
	private Tournament tournament;
	
	
	@OneToOne(optional=true)
	@JoinColumn(name="teamAId")
	private Team teamA; 
	
	@OneToOne(optional=true)
	@JoinColumn(name="teamBId")
	private Team teamB; 
	
	
	@Column
	private Date createdDate;
	@Column
	private Date lastModifiedDate;
	@Column
	private String createdBy;
	@Column
	private String lastModifiedBy;

	public Game(){
		
	}
	
	public Game(Integer gameNo2) {
		this.gameNo=gameNo2;
	}

	public Integer getGameNo() {
		return gameNo;
	}

	public void setGameNo(Integer gameNo) {
		this.gameNo = gameNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	

	public Boolean getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Boolean lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPersonePointMultiplier() {
		return personePointMultiplier;
	}

	public void setPersonePointMultiplier(Integer personePointMultiplier) {
		this.personePointMultiplier = personePointMultiplier;
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

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Integer getGameTournamentSequenceNo() {
		return gameTournamentSequenceNo;
	}

	public void setGameTournamentSequenceNo(Integer gameTournamentSequenceNo) {
		this.gameTournamentSequenceNo = gameTournamentSequenceNo;
	}

	public Team getTeamA() {
		return teamA;
	}

	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}
	
	
	
	
	
}
