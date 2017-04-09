package com.play.app.model;

import java.util.Date;

public class VoteInfoModel {

			
		private Integer gameNo;
		private Integer personId;
		private Integer tournamentId;
		private Integer teamAId;
		private Integer teamBId ;
		private Double teamAValue;
		private Double teamBValue;
		private boolean participated;
		private Date   votingTime;
		private String message;
		private Integer modifiedcounter;
		private boolean updatedStatus;
		
		public Integer getGameNo() {
			return gameNo;
		}
		public void setGameNo(Integer gameNo) {
			this.gameNo = gameNo;
		}
		public Integer getPersonId() {
			return personId;
		}
		public void setPersoneId(Integer personId) {
			this.personId = personId;
		}
		public Integer getTournamentId() {
			return tournamentId;
		}
		public void setTournamentId(Integer tournamentId) {
			this.tournamentId = tournamentId;
		}
		public Integer getTeamAId() {
			return teamAId;
		}
		public void setTeamAId(Integer teamAId) {
			this.teamAId = teamAId;
		}
		public Integer getTeamBId() {
			return teamBId;
		}
		public void setTeamBId(Integer teamBId) {
			this.teamBId = teamBId;
		}
		public Double getTeamAValue() {
			return teamAValue;
		}
		public void setTeamAValue(Double teamAValue) {
			this.teamAValue = teamAValue;
		}
		public Double getTeamBValue() {
			return teamBValue;
		}
		public void setTeamBValue(Double teamBValue) {
			this.teamBValue = teamBValue;
		}
		public boolean isParticipated() {
			return participated;
		}
		public void setParticipated(boolean participated) {
			this.participated = participated;
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Integer getModifiedcounter() {
			return modifiedcounter;
		}
		public void setModifiedcounter(Integer modifiedcounter) {
			this.modifiedcounter = modifiedcounter;
		}
		public boolean isUpdatedStatus() {
			return updatedStatus;
		}
		public void setUpdatedStatus(boolean updatedStatus) {
			this.updatedStatus = updatedStatus;
		}
		public Date getVotingTime() {
			return votingTime;
		}
		public void setVotingTime(Date votingTime) {
			this.votingTime = votingTime;
		}
		@Override
		public String toString() {
			return "VoteInfoModel [gameNo=" + gameNo + ", personId=" + personId + ", tournamentId=" + tournamentId
					+ ", teamAId=" + teamAId + ", teamBId=" + teamBId + ", teamAValue=" + teamAValue + ", teamBValue="
					+ teamBValue + ", participated=" + participated + ", votingTime=" + votingTime + ", message="
					+ message + ", modifiedcounter=" + modifiedcounter + ", updatedStatus=" + updatedStatus + "]";
		}
		
		
		
}
