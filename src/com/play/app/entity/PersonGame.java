package com.play.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity @IdClass(PersoneGameId.class)
public class PersonGame implements Serializable{

	@Id
	@Column(insertable = false, updatable = false)
	private Integer personId;
	
	@Id
	@Column(insertable = false, updatable = false)
	private Integer tournamentId;	
	@Id
	@Column(name="game_No",insertable = false, updatable = false)
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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="game_No",referencedColumnName="gameNo")
	private Game game; 

	@ManyToOne(optional=false)
	@JoinColumn(name="personId",referencedColumnName="personId")
	private Person person;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="tournamentId",referencedColumnName="tournamentId")
	private Tournament tournament;
	
	@Column(nullable = false)
	private Date createdDate;

	@Column(nullable = false)
	private Date lastModifiedDate;

	@Column()
	private String createdBy;

	@Column()
	private String lastModifiedBy;
	
	
	
	public PersonGame(){
		
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
		
		if(points!=null && points.isNaN()==false){
			BigDecimal a = new BigDecimal(points);
			BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			this.points = roundOff.doubleValue();
		}
		else
		this.points = points;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Double getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Double totalPoints) {
		
		if(totalPoints!=null && totalPoints.isNaN()==false){
			BigDecimal a = new BigDecimal(totalPoints);
			BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			this.totalPoints = roundOff.doubleValue();
		}
		else
		this.totalPoints = totalPoints;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	
	
	
	
	
}
