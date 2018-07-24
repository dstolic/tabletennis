package com.ds.microservices.sport.tabletennis.dto;

public class CompetitionPlayerPKDto {

	private Long competitionId;

	private PlayerDto player;

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