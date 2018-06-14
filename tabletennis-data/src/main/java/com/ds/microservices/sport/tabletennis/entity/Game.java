package com.ds.microservices.sport.tabletennis.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the games database table.
 * 
 */
@Entity
@Table(name="games")
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private byte finished;

	@Column(name="points_away")
	private int pointsAway;

	@Column(name="points_home")
	private int pointsHome;

	@ManyToOne
	@JoinColumn(name="player_away")
	private Player playerAway;

	@ManyToOne
	@JoinColumn(name="player_home")
	private Player playerHome;
	
//	@Transient
//	@JoinColumn(name="competition_id")
//	private Competition competition;

	@JsonIgnore // budzevina, resiti kasnije
    @ManyToOne
    @JoinColumn(name = "competition_id")
	private Competition competition;

 //	@Column(name="competition_id")
//	private Long competitionId;

//	@Transient
//	private Player playerHome;

//	@Transient
//	private Player playerAway;

	@Column(name="group_num")
	private Long groupNum;

	private int round;

	public Game() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getFinished() {
		return this.finished;
	}

	public void setFinished(byte finished) {
		this.finished = finished;
	}

	public int getPointsAway() {
		return this.pointsAway;
	}

	public void setPointsAway(int pointsAway) {
		this.pointsAway = pointsAway;
	}

	public int getPointsHome() {
		return this.pointsHome;
	}

	public void setPointsHome(int pointsHome) {
		this.pointsHome = pointsHome;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

//	public Long getCompetitionId() {
//		return competitionId;
//	}
//
//	public void setCompetitionId(Long competitionId) {
//		this.competitionId = competitionId;
//	}

	public Player getPlayerHome() {
		return playerHome;
	}

	public void setPlayerHome(Player playerHome) {
		this.playerHome = playerHome;
	}

	public Player getPlayerAway() {
		return playerAway;
	}

	public void setPlayerAway(Player playerAway) {
		this.playerAway = playerAway;
	}

	public Long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Long groupNum) {
		this.groupNum = groupNum;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", competition=" + competition.getId() + ", playerHome=" + playerHome + ", playerAway="
				+ playerAway + ", groupNum=" + groupNum + ", round=" + round + "]";
	}

}