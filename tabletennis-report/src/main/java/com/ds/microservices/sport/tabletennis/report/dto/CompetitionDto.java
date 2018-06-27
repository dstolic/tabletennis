package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Competition")
public class CompetitionDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String type;

	private String description;

	private String name;

	private boolean completed;

	private boolean current;

//	private Set<PlayerDto> players;

//	@JsonIgnore
//	@JsonBackReference
    private Set<CompetitionProperty> properties;
    
	private List<CompetitionPlayer> competitionPlayers;

	private List<Group> groups;
    
	private List<GameDto> games;

	
	public CompetitionDto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set<PlayerDto> getPlayers() {
//		return players;
//	}

//	public void setCompetitionPlayers(Set<PlayerDto> players) {
//		this.players = players;
//	}

//	public void setPlayers(Set<PlayerDto> players) {
//		this.players = players;
//	}

	public Set<CompetitionProperty> getProperties() {
		return properties;
	}

	public void setProperties(Set<CompetitionProperty> properties) {
		this.properties = properties;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public List<GameDto> getGames() {
		return games;
	}

	public void setGames(List<GameDto> games) {
		this.games = games;
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

//	@Override
//	public String toString() {
//		return "CompetitionDto [id=" + id + ", description=" + description + ", name=" + name
//				+ "]";
//	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}