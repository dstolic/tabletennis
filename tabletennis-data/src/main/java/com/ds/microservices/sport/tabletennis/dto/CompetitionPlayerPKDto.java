package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;

public class CompetitionPlayerPKDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String competitionId;

	private String playerId;

	public CompetitionPlayerPKDto() {
	}

	public String getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public String getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

}