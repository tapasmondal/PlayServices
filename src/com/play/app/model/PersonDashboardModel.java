package com.play.app.model;

public class PersonDashboardModel {
	
	private Integer personId;
	private String  personTitle;
	private Integer tournamentId;
	private String tournamentTitle;
	private Integer teamId;
	private String teamTitle;
	private Double  totalPoints;
	private Integer ranking;
	private Integer gamePlayed;
	private Integer gameLeft;
	private Integer win;
	private Integer loss;
	private Integer draw;
	private Boolean participate;
	private Double balance;
	
	private PersonGameModel currentPersonGameModel;
	
	private PersonGameModel lastPersonGameModel;

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public void setPersonTitle(String personTitle) {
		this.personTitle = personTitle;
	}

	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}

	public void setTournamentTitle(String tournamentTitle) {
		this.tournamentTitle = tournamentTitle;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}

	public void setTotalPoints(Double totalPoints) {
		this.totalPoints = totalPoints;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public void setGamePlayed(Integer gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public void setGameLeft(Integer gameLeft) {
		this.gameLeft = gameLeft;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public void setLoss(Integer loss) {
		this.loss = loss;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public void setCurrentPersonGameModel(PersonGameModel currentPersonGameModel) {
		this.currentPersonGameModel = currentPersonGameModel;
	}

	

	public Integer getPersonId() {
		return personId;
	}

	public String getPersonTitle() {
		return personTitle;
	}

	public Integer getTournamentId() {
		return tournamentId;
	}

	public String getTournamentTitle() {
		return tournamentTitle;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	public Double getTotalPoints() {
		return totalPoints;
	}

	public Integer getRanking() {
		return ranking;
	}

	public Integer getGamePlayed() {
		return gamePlayed;
	}

	public Integer getGameLeft() {
		return gameLeft;
	}

	public Integer getWin() {
		return win;
	}

	public Integer getLoss() {
		return loss;
	}

	public Integer getDraw() {
		return draw;
	}

	public PersonGameModel getCurrentPersonGameModel() {
		return currentPersonGameModel;
	}

	public PersonGameModel getLastPersonGameModel() {
		return lastPersonGameModel;
	}

	public void setLastPersonGameModel(PersonGameModel lastPersonGameModel) {
		this.lastPersonGameModel = lastPersonGameModel;
	}

	public Boolean getParticipate() {
		return participate;
	}

	public void setParticipate(Boolean participate) {
		this.participate = participate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "PersonDashboardModel [personId=" + personId + ", personTitle=" + personTitle + ", tournamentId="
				+ tournamentId + ", tournamentTitle=" + tournamentTitle + ", teamId=" + teamId + ", teamTitle="
				+ teamTitle + ", totalPoints=" + totalPoints + ", ranking=" + ranking + ", gamePlayed=" + gamePlayed
				+ ", gameLeft=" + gameLeft + ", win=" + win + ", loss=" + loss + ", draw=" + draw + ", participate="
				+ participate + ", balance=" + balance + ", currentPersonGameModel=" + currentPersonGameModel
				+ ", lastPersonGameModel=" + lastPersonGameModel + ", getPersonId()=" + getPersonId()
				+ ", getPersonTitle()=" + getPersonTitle() + ", getTournamentId()=" + getTournamentId()
				+ ", getTournamentTitle()=" + getTournamentTitle() + ", getTeamId()=" + getTeamId()
				+ ", getTeamTitle()=" + getTeamTitle() + ", getTotalPoints()=" + getTotalPoints() + ", getRanking()="
				+ getRanking() + ", getGamePlayed()=" + getGamePlayed() + ", getGameLeft()=" + getGameLeft()
				+ ", getWin()=" + getWin() + ", getLoss()=" + getLoss() + ", getDraw()=" + getDraw()
				+ ", getCurrentPersonGameModel()=" + getCurrentPersonGameModel() + ", getLastPersonGameModel()="
				+ getLastPersonGameModel() + ", getParticipate()=" + getParticipate() + ", getBalance()=" + getBalance()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	

	
	
	
	
}
