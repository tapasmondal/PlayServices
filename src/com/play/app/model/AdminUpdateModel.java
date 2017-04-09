package com.play.app.model;

import java.util.Date;

public class AdminUpdateModel {

	
private Integer	gameNo;
private Integer	adminId;
private Integer	tournamentId;
private boolean	gameLockStatus;
private Integer	gameLockDelayBy;
private String	gameStatus;
private Integer	gameWinnerId;
		
private String	message;
private boolean	updatedStatus;

private Date updatedDate;


public Integer getGameNo() {
	return gameNo;
}
public void setGameNo(Integer gameNo) {
	this.gameNo = gameNo;
}
public Integer getAdminId() {
	return adminId;
}
public void setAdminId(Integer adminId) {
	this.adminId = adminId;
}
public Integer getTournamentId() {
	return tournamentId;
}
public void setTournamentId(Integer tournamentId) {
	this.tournamentId = tournamentId;
}
public boolean isGameLockStatus() {
	return gameLockStatus;
}
public void setGameLockStatus(boolean gameLockStatus) {
	this.gameLockStatus = gameLockStatus;
}
public Integer getGameLockDelayBy() {
	return gameLockDelayBy;
}
public void setGameLockDelayBy(Integer gameLockDelayBy) {
	this.gameLockDelayBy = gameLockDelayBy;
}
public String getGameStatus() {
	return gameStatus;
}
public void setGameStatus(String gameStatus) {
	this.gameStatus = gameStatus;
}
public Integer getGameWinnerId() {
	return gameWinnerId;
}
public void setGameWinnerId(Integer gameWinnerId) {
	this.gameWinnerId = gameWinnerId;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

public boolean isUpdatedStatus() {
	return updatedStatus;
}
public void setUpdatedStatus(boolean updatedStatus) {
	this.updatedStatus = updatedStatus;
}
public Date getUpdatedDate() {
	return updatedDate;
}
public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
}

@Override
public String toString() {
	return "AdminUpdateModel [gameNo=" + gameNo + ", adminId=" + adminId + ", tournamentId=" + tournamentId
			+ ", gameLockStatus=" + gameLockStatus + ", gameLockDelayBy=" + gameLockDelayBy + ", gameStatus="
			+ gameStatus + ", gameWinnerId=" + gameWinnerId + ", message=" + message + ", updatedStatus="
			+ updatedStatus + ", updatedDate=" + updatedDate + "]";
}





}
