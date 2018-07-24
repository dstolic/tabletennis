package com.ds.microservices.sport.tabletennis.report.dto;

public class GameSetDto {
	
	private GameSetIdDto id; 

	private int pointsHome;

	private int pointsAway;

	public int getPointsHome() {
		return pointsHome;
	}

	public void setPointsHome(int pointsHome) {
		this.pointsHome = pointsHome;
	}

	public int getPointsAway() {
		return pointsAway;
	}

	public void setPointsAway(int pointsAway) {
		this.pointsAway = pointsAway;
	}

	public GameSetIdDto getId() {
		return id;
	}

	public void setId(GameSetIdDto id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GameSet [id=" + id + ", pointsHome=" + pointsHome + ", pointsAway=" + pointsAway + "]";
	}

}