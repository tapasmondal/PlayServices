package com.play.app.model;

public class PersonModel {
	
	private Integer personId;
	private String personTitle;
	private Boolean participate;
	private Double perviousBalance;
	private Double balance;
	private Boolean status;
	private String message;
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Boolean getParticipate() {
		return participate;
	}
	public void setParticipate(Boolean participate) {
		this.participate = participate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getPersonTitle() {
		return personTitle;
	}
	public void setPersonTitle(String personTitle) {
		this.personTitle = personTitle;
	}
	
	
	
	public Double getPerviousBalance() {
		return perviousBalance;
	}
	public void setPerviousBalance(Double perviousBalance) {
		this.perviousBalance = perviousBalance;
	}
	@Override
	public String toString() {
		return "PersonModel [personId=" + personId + ", personTitle=" + personTitle + ", participate=" + participate
				+ ", perviousBalance=" + perviousBalance + ", balance=" + balance + ", status=" + status + ", message="
				+ message + "]";
	}
	
	
	

}
