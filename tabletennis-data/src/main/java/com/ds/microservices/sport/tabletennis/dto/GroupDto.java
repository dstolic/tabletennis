package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Group")
public class GroupDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private List<PlayerDto> players;
	
	private List<GameDto> games;
	
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


}