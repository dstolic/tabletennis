package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.Player;

public class CompetitionPlayerPKDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Competition competition;

	private Player player;

	public CompetitionPlayerPKDto() {
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


}