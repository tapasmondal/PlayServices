package com.play.app.dto;

import java.util.Date;


public class PersonGameDTO {

	
	private Integer personId;
	private Integer tournamentId;	
	private Integer gameNo;
	private Double teamAId;
	private Double teamBId;
	private Double points;
	private String result;
	private boolean participated;
	private Integer modifiedCount;
	private GameDTO game; 
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	private Double totalPoint;
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Integer getGameNo() {
		return gameNo;
	}
	public void setGameNo(Integer gameNo) {
		this.gameNo = gameNo;
	}
	public Double getTeamAId() {
		return teamAId;
	}
	public void setTeamAId(Double teamAId) {
		this.teamAId = teamAId;
	}
	public Double getTeamBId() {
		return teamBId;
	}
	public void setTeamBId(Double teamBId) {
		this.teamBId = teamBId;
	}
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public boolean isParticipated() {
		return participated;
	}
	public void setParticipated(boolean participated) {
		this.participated = participated;
	}
	public Integer getModifiedCount() {
		return modifiedCount;
	}
	public void setModifiedCount(Integer modifiedCount) {
		this.modifiedCount = modifiedCount;
	}
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
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
	public Double getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Double totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	
}
