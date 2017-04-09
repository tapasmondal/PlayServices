package com.play.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity @IdClass(TeamPerformanceId.class)
public class TeamPerformance {
	
	@Id
	@Column(name = "team_Id",insertable = false, updatable = false)
	private Integer teamId;
	
	@Id
	private Integer tournamentId;
	
	@Column(nullable = false)
	private Integer plyed;
	
	@Column(nullable = false)
	private Integer planed;
	
	@Column(nullable = false)
	private Integer win;
	
	@Column(nullable = false)
	private Integer loss;
	
	@Column(nullable = false)
	private Integer draw;
	
	@Column(nullable = false)
	private Integer cancel;
	
	@Column(nullable = false)
	private Integer points;

	@Column
	private Date createdDate;
	@Column
	private Date lastModifiedDate;
	@Column
	private String createdBy;
	@Column
	private String lastModifiedBy;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="team_Id",referencedColumnName="teamId")
	private Team Team;

	
	public TeamPerformance(){
		
	}
	
	public Integer getPlyed() {
		return plyed;
	}

	public void setPlyed(Integer plyed) {
		this.plyed = plyed;
	}

	public Integer getPlaned() {
		return planed;
	}

	public void setPlaned(Integer planed) {
		this.planed = planed;
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

	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Team getTeam() {
		return Team;
	}

	public void setTeam(Team team) {
		Team = team;
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
