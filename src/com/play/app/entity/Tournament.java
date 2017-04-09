package com.play.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tournament{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tournamentId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String tournamentDesc;

	@Column(nullable = false)
	private Integer gamePlanned;
	
	@Column(nullable = false)
	private Integer gamePlayed;
	
	@Column(nullable = false)
	private Integer year;
	
	@Column(nullable = false)
	private Integer nextGameNo;
	
	@Column(nullable = false)
	private Boolean active;

	@Column(nullable = false)
	private Double personPointBaseValue;
	
	@Column(nullable = false)
	private Integer personPointNextIncrementValue;
	
	
	@Column(nullable = false)
	private Integer teamPointBaseValue;
	
	@Column
	private Date createdDate;
	@Column
	private Date lastModifiedDate;
	@Column
	private String createdBy;
	@Column
	private String lastModifiedBy;
	
	

	public Tournament() {
		super();
	}

	public Tournament(Integer tournament) {
		this.tournamentId=tournament;
	}

	public String getTournamentDesc() {
		return tournamentDesc;
	}

	public void setTournamentDesc(String tournamentDesc) {
		this.tournamentDesc = tournamentDesc;
	}

	public Integer getGamePlanned() {
		return gamePlanned;
	}

	public void setGamePlanned(Integer gamePlanned) {
		this.gamePlanned = gamePlanned;
	}

	public Integer getGamePlayed() {
		return gamePlayed;
	}

	public void setGamePlayed(Integer gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getNextGameNo() {
		return nextGameNo;
	}

	public void setNextGameNo(Integer nextGameNo) {
		this.nextGameNo = nextGameNo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	

	public Integer getTeamPointBaseValue() {
		return teamPointBaseValue;
	}

	public void setTeamPointBaseValue(Integer teamPointBaseValue) {
		this.teamPointBaseValue = teamPointBaseValue;
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

	public Integer getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
