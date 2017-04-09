package com.play.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(length = 100,nullable = false)
	private Integer personId;
	
	@Column(length = 100)
	private String title;
	
	@Column
	private Boolean oldParticipateStatus;
	
	@Column
	private Boolean newParticipateStatus;
	
	@Column
	private Double oldBalance;
	
	@Column
	private Double newBalance;
	
	@Column
	private String createdBy;
	@Column
	private Date createdDate;
	
	
	public PersonHistory() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getOldParticipateStatus() {
		return oldParticipateStatus;
	}
	public void setOldParticipateStatus(Boolean oldParticipateStatus) {
		this.oldParticipateStatus = oldParticipateStatus;
	}
	public Boolean getNewParticipateStatus() {
		return newParticipateStatus;
	}
	public void setNewParticipateStatus(Boolean newParticipateStatus) {
		this.newParticipateStatus = newParticipateStatus;
	}
	public Double getOldBalance() {
		return oldBalance;
	}
	public void setOldBalance(Double oldBalance) {
		this.oldBalance = oldBalance;
	}
	public Double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(Double newBalance) {
		this.newBalance = newBalance;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
