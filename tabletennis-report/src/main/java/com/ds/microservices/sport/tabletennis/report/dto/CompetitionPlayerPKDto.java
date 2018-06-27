package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;

public class CompetitionPlayerPKDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private CompetitionDto competition;

	private PlayerDto player;

	public CompetitionPlayerPKDto() {
	}

	public CompetitionDto getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionDto competition) {
		this.competition = competition;
	}

	public PlayerDto getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDto player) {
		this.player = player;
	}


}