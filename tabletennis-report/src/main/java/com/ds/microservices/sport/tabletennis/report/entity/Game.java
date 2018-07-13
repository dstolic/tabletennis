package com.ds.microservices.sport.tabletennis.report.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="games")
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private int finished;

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
	
	@JsonIgnore // budzevina, resiti kasnije
    @ManyToOne
    @JoinColumn(name = "competition_id")
	private Competition competition;

	@Column(name="group_id")
	private Long groupId;

	private int round;

	@OneToMany(mappedBy="id.game")
	private List<GameSet> sets;
	
	public Game() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFinished() {
		return this.finished;
	}

	public void setFinished(int finished) {
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

//	public Long getGroupNum() {
//		return groupNum;
//	}
//
//	public void setGroupNum(Long groupNum) {
//		this.groupNum = groupNum;
//	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public List<GameSet> getSets() {
		return sets;
	}

	public void setSets(List<GameSet> sets) {
		this.sets = sets;
	}

//	@Override
//	public String toString() {
//		return "Game [id=" + id + ", competition=" + competition.getId() + ", playerHome=" + playerHome + ", playerAway="
//				+ playerAway + "," + ", round=" + round + "]";
//	}

}