package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;

public class CompetitionPlayerPKDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long competitionId;

	private PlayerDto player;

	public CompetitionPlayerPKDto() {
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public PlayerDto getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDto player) {
		this.player = player;
	}

}