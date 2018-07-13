package com.ds.microservices.sport.tabletennis.report.entity;

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

@Entity
@Table(name="game_set")
@NamedQuery(name="Game_set.findAll", query="SELECT g FROM Game g")
public class GameSet implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;

////	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "game_id")
//	private Game game;

//	@Column(name="set_no")
//	private int setNo;
	
	@EmbeddedId
	private GameSetId id; 

	@Column(name="points_home")
	private int pointsHome;

	@Column(name="points_away")
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

//	public int getGameId() {
//		return gameId;
//	}
//
//	public void setGameId(int gameId) {
//		this.gameId = gameId;
//	}

	@Override
	public String toString() {
		return "GameSet [id=" + id + ", pointsHome=" + pointsHome + ", pointsAway=" + pointsAway + "]";
	}

	public GameSetId getId() {
		return id;
	}

	public void setId(GameSetId id) {
		this.id = id;
	}

}