package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;


public class CompetitionPlayerDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private CompetitionPlayerPKDto id;

	private CompetitionDto competition;

	private PlayerDto player;

	private boolean seed;

	private Long groupNum;

	public CompetitionPlayerDto() {
	}

	public CompetitionPlayerPKDto getId() {
		return id;
	}

	public void setId(CompetitionPlayerPKDto id) {
		this.id = id;
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

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}

	public Long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Long groupNum) {
		this.groupNum = groupNum;
	}
	

}