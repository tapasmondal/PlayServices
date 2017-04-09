package com.play.app.type;

public enum UserStatus {

	USER_STATUS_ACTIVE("A"), 
	USER_STATUS_DISABLED("D"), 
	USER_STATUS_LOCKED("L");

	private String userStatus;

	private UserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserStatus() {
		return this.userStatus;
	}
}