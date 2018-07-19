package com.ds.microservices.sport.tabletennis.report.dto;

import java.util.List;
import java.util.Set;

import com.ds.microservices.sport.tabletennis.report.entity.CompetitionProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Competition")
public class CompetitionDto {

	private Long id;

	private String category;

	private String description;

	private String name;

	private boolean completed;

	private boolean current;

	private Set<PlayerDto> players;

//	@JsonIgnore
//	@JsonBackReference
    private Set<CompetitionProperty> properties;
    
	private List<CompetitionPlayerDto> competitionPlayers;

	private List<GroupDto> groups;
    
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

	public List<GroupDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDto> groups) {
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

	public List<CompetitionPlayerDto> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayerDto> competitionPlayers) {
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

	public Set<PlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerDto> players) {
		this.players = players;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}