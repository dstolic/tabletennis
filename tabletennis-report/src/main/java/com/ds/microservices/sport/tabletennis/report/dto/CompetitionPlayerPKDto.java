package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;

public class CompetitionPlayerPKDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long competitionId;

	private Long playerId;

	public CompetitionPlayerPKDto() {
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}


}