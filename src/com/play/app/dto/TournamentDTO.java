package com.play.app.dto;

import java.util.Date;

public class TournamentDTO {

	
	private Integer tournamentId;
	private String title;
	private String tournamentDesc;
	private Integer gamePlanned;
	private Integer gamePlayed;
	private Integer year;
	private Integer nextGameNo;
	private Boolean active;
	private Double personPointBaseValue;
	private Integer personPointNextIncrementValue;
	private Integer teamPointBaseValue;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	
	
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
	public Integer getTeamPointBaseValue() {
		return teamPointBaseValue;
	}
	public void setTeamPointBaseValue(Integer teamPointBaseValue) {
		this.teamPointBaseValue = teamPointBaseValue;
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
	
	
	
}
