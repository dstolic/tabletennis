package com.ds.microservices.sport.tabletennis.report.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Player")
public class PlayerDto {

	private Long id;

	private boolean active;

	private String firstName;

	private String lastName;

	private Long points;

	private int gamesPointsFor;

	private int gamesPointsAgainst;

	private int groupPoints;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getGamesPointsFor() {
		return gamesPointsFor;
	}

	public void setGamesPointsFor(int gamesPointsFor) {
		this.gamesPointsFor = gamesPointsFor;
	}

	public int getGamesPointsAgainst() {
		return gamesPointsAgainst;
	}

	public void setGamesPointsAgainst(int gamesPointsAgainst) {
		this.gamesPointsAgainst = gamesPointsAgainst;
	}

	public int getGroupPoints() {
		return groupPoints;
	}

	public void setGroupPoints(int groupPoints) {
		this.groupPoints = groupPoints;
	}

	public void addGroupPoints(int increment) {
		groupPoints += increment;
		return ;
	}

	public void addGamesPointsFor(int increment) {
		gamesPointsFor += increment;
		return ;
	}

	public void addGamesPointsAgainst(int increment) {
		gamesPointsAgainst += increment;
		return ;
	}

}