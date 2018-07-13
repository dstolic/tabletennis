package com.ds.microservices.sport.tabletennis.report.dto;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Group")
public class GroupDto {

	private Long id;

	private String name;
	
	private List<PlayerDto> players;
	
	private List<GameDto> games;
	
	private List<CompetitionPlayer> competitionPlayers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDto> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", players=" + players + "]";
	}

	public List<GameDto> getGames() {
		return games;
	}

	public void setGames(List<GameDto> games) {
		this.games = games;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}


}