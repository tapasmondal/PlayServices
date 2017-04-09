package com.play.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity @IdClass(PersonTournamentId.class)
public class PersonTournament implements Comparable<PersonTournament>{

	@Id
	@Column( name="personId",insertable = false, updatable = false)
	private Integer personId;
	
	@Id
	@Column(name = "tournament_Id",insertable = false, updatable = false)
	private Integer tournamentId;
	
	@Column
	private Integer teamId;
	
	@Column
	private Double totalPoints;
	
	@Column
	private Integer ranking;
	
	@Column(nullable = false)
	private Integer gamePlayed;
	
	@Column(nullable = false)
	private Integer gameLeft;
	
	@Column(nullable = false)
	private Integer win;
	
	@Column(nullable = false)
	private Integer loss;
	
	@Column(nullable = false)
	private Integer draw;
	
	@Column(nullable = false)
	private Integer cancelled;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="tournament_Id",referencedColumnName="tournamentId")
	 Tournament tournament;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="personId",referencedColumnName="personId")
	 Person person;
	
	@Column
	private Date createdDate;
	@Column
	private Date lastModifiedDate;
	@Column
	private String createdBy;
	@Column
	private String lastModifiedBy;
	
	
	public PersonTournament() {
		// TODO Auto-generated constructor stub
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
		else{
		this.totalPoints = totalPoints;
		}
	}
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	
	public Integer getPersonId() {
		return personId;
	}


	public void setPersonId(Integer personId) {
		this.personId = personId;
	}



	public Integer getTeamId() {
		return teamId;
	}


	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

/*
	public Tournament getTournamentDetails() {
		return tournamentDetails;
	}


	public void setTournamentDetails(Tournament tournamentDetails) {
		this.tournamentDetails = tournamentDetails;
	}*/


	public Integer getGamePlayed() {
		return gamePlayed;
	}


	public void setGamePlayed(Integer gamePlayed) {
		this.gamePlayed = gamePlayed;
	}


	public Integer getGameLeft() {
		return gameLeft;
	}


	public void setGameLeft(Integer gameLeft) {
		this.gameLeft = gameLeft;
	}


	public Integer getWin() {
		return win;
	}


	public void setWin(Integer win) {
		this.win = win;
	}


	public Integer getLoss() {
		return loss;
	}


	public void setLoss(Integer loss) {
		this.loss = loss;
	}


	public Integer getDraw() {
		return draw;
	}


	public void setDraw(Integer draw) {
		this.draw = draw;
	}


	public Integer getCancelled() {
		return cancelled;
	}


	public void setCancelled(Integer cancelled) {
		this.cancelled = cancelled;
	}


	public Integer getTournamentId() {
		return tournamentId;
	}


	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}


	public Tournament getTournament() {
		return tournament;
	}


	public void setTournament(Tournament tournament) {
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


	@Override
	public int compareTo(PersonTournament o) {
		
        /* For Descending order do like this */
        return o.getTotalPoints().intValue()-this.getTotalPoints().intValue();
    
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}

	
	
}
