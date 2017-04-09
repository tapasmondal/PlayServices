package com.play.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="persongame") @IdClass(PersoneGameId.class)
public class PersonGameTemp implements Serializable{

	@Id
	private Integer personId;
	
	@Id
	private Integer tournamentId;	
	@Id
	@Column(name="game_No")
	private Integer gameNo;
	
	
	@Column(nullable = false)
	private Double teamAId;
	
	@Column(nullable = false)
	private Double teamBId;
	
	@Column
	private Double points;
	
	@Column
	private String result;
	
	@Column
	private Double totalPoints;
	
	@Column(nullable = false)
	private boolean participated;
	
	@Column
	private Integer modifiedCount;
	
	
	@Column(nullable = false)
	private Date createdDate;

	@Column(nullable = false)
	private Date lastModifiedDate;

	@Column()
	private String createdBy;

	@Column()
	private String lastModifiedBy;
	
	
	
	public PersonGameTemp(){
		
	}
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
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
	public Integer getGameNo() {
		return gameNo;
	}
	public void setGameNo(Integer gameNo) {
		this.gameNo = gameNo;
	}
	
	public Double getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Double totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	
	
	
	
	
}
