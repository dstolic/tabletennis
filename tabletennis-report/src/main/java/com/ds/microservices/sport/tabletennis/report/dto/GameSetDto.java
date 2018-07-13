package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

	@Override
	public String toString() {
		return "GameSet [id=" + id + ", pointsHome=" + pointsHome + ", pointsAway=" + pointsAway + "]";
	}

	public GameSetIdDto getId() {
		return id;
	}

	public void setId(GameSetIdDto id) {
		this.id = id;
	}

}