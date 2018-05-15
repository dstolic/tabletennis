package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("LeagueGame")
public class LeagueGameDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	//bi-directional many-to-one association to Game
	@ManyToOne
	private GameDto game;

	//bi-directional many-to-one association to League
	@ManyToOne
	private CompetitionDto league;

	public LeagueGameDto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameDto getGame() {
		return this.game;
	}

	public void setGame(GameDto game) {
		this.game = game;
	}

	public CompetitionDto getLeague() {
		return this.league;
	}

	public void setLeague(CompetitionDto league) {
		this.league = league;
	}

}