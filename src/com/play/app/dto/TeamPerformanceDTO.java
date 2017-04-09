package com.play.app.dto;

import java.util.Date;
import java.util.List;

public class TeamPerformanceDTO {
	
	
	private Integer teamId;
	private String title;
	private String decs;
	private Integer tournamentId;
	private Integer plyed;
	private Integer planed;
	private Integer win;
	private Integer loss;
	private Integer draw;
	private Integer cancel;
	private Integer points;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;
	
	private TeamDTO Team;
	
	private List<GameDTO> games;
	
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
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
	public TeamDTO getTeam() {
		return Team;
	}
	public void setTeam(TeamDTO team) {
		Team = team;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecs() {
		return decs;
	}
	public void setDecs(String decs) {
		this.decs = decs;
	}
	public List<GameDTO> getGames() {
		return games;
	}
	public void setGames(List<GameDTO> games) {
		this.games = games;
	}
	
	
}
