package com.ds.microservices.sport.tabletennis.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the competition_player database table.
 * 
 */
@Entity
@Table(name="competition_player")
@NamedQuery(name="CompetitionPlayer.findAll", query="SELECT c FROM CompetitionPlayer c")
public class CompetitionPlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//bi-directional many-to-one association to Competition
	@ManyToOne
	@JoinColumn(name = "competition_id")
	private Competition competition;

	//bi-directional many-to-one association to Player
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	//bi-directional many-to-one association to Game
//	@OneToMany(mappedBy="competitionPlayer1")
//	private List<Game> games1;

	//bi-directional many-to-one association to Game
//	@OneToMany(mappedBy="competitionPlayer2")
//	private List<Game> games2;

	public CompetitionPlayer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

//	public List<Game> getGames1() {
//		return this.games1;
//	}
//
//	public void setGames1(List<Game> games1) {
//		this.games1 = games1;
//	}
//
//	public Game addGames1(Game games1) {
//		getGames1().add(games1);
//		games1.setCompetitionPlayer1(this);
//
//		return games1;
//	}
//
//	public Game removeGames1(Game games1) {
//		getGames1().remove(games1);
//		games1.setCompetitionPlayer1(null);
//
//		return games1;
//	}
//
//	public List<Game> getGames2() {
//		return this.games2;
//	}
//
//	public void setGames2(List<Game> games2) {
//		this.games2 = games2;
//	}
//
//	public Game addGames2(Game games2) {
//		getGames2().add(games2);
//		games2.setCompetitionPlayer2(this);
//
//		return games2;
//	}
//
//	public Game removeGames2(Game games2) {
//		getGames2().remove(games2);
//		games2.setCompetitionPlayer2(null);
//
//		return games2;
//	}

}