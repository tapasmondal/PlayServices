package com.play.app.model;

public class UserRegistrationModel {
	
private String password;
private String userName;
private String confirmPassword;
private String token;
private Integer teamId;
private Integer tournamentId;

private boolean registrationStatus;
private String message;

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
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
public boolean isRegistrationStatus() {
	return registrationStatus;
}
public void setRegistrationStatus(boolean registrationStatus) {
	this.registrationStatus = registrationStatus;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}




}
