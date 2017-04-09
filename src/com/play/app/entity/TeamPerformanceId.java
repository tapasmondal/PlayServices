package com.play.app.entity;

import java.io.Serializable;

public class TeamPerformanceId implements Serializable {

	private Integer teamId;
	private Integer tournamentId;
	
	public TeamPerformanceId(Integer tournamentId2, Integer teamId2) {
		this.tournamentId=tournamentId2;
		this.teamId=teamId2;
	}

	public TeamPerformanceId() {
		super();
	}
	
}
