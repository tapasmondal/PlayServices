package com.play.app.dto;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;

public class UserDTO {

	private Integer userId;
	
	private Boolean admin;

	private String password;

	private String userName;

	private String status;

	private Boolean enabled;
	
	private Boolean forcePasswordChange;

	private byte[] profileImage;

	private String userRoles;
	
    private PersonDTO person;   
	
	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private String createdBy;
	
	private String lastModifiedBy;
	
	private String email;
	
	private String deviceID;
	
	private String authenticationStatus;
	
	private String token;
	
	private Integer teamId;
	
	private boolean registrationStatus;
	
	private String message;
	
	private Integer tournamentId;
	
	private Integer personId;
	
	private String personViewTitle;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getForcePasswordChange() {
		return forcePasswordChange;
	}
	public void setForcePasswordChange(Boolean forcePasswordChange) {
		this.forcePasswordChange = forcePasswordChange;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	public PersonDTO getPerson() {
		return person;
	}
	public void setPerson(PersonDTO person) {
		this.person = person;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getAuthenticationStatus() {
		return authenticationStatus;
	}
	public void setAuthenticationStatus(String authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
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
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", admin=" + admin + ", userName=" + userName + ", status=" + status
				+ ", enabled=" + enabled + ", forcePasswordChange=" + forcePasswordChange + ", profileImage="
				+ Arrays.toString(profileImage) + ", userRoles=" + userRoles + ", person=" + person + ", createdDate="
				+ createdDate + ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", email=" + email + ", deviceID=" + deviceID
				+ ", authenticationStatus=" + authenticationStatus + ", token=" + token + ", teamId=" + teamId
				+ ", registraionStatus=" + registrationStatus + ", message=" + message + ", tournamentId=" + tournamentId
				+ ", personId=" + personId + "]";
	}
	public String getPersonViewTitle() {
		return personViewTitle;
	}
	public void setPersonViewTitle(String personViewTitle) {
		this.personViewTitle = personViewTitle;
	}
	public boolean isRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(boolean registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	
	
	

}