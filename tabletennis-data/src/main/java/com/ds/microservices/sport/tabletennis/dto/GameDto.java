package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Game")
public class GameDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private boolean finished;

	private int pointsAway;

	private int pointsHome;

	@JsonIgnore
	private CompetitionDto competition;

	private PlayerDto playerHome;

	private PlayerDto playerAway;

	private Long groupNum;

	private int round;

	public GameDto() {
	} 
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getFinished() {
		return this.finished;
	}

	public void setFinished(boolean finished) {
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

	public CompetitionDto getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionDto competition) {
		this.competition = competition;
	}

	public PlayerDto getPlayerHome() {
		return playerHome;
	}

	public void setPlayerHome(PlayerDto playerHome) {
		this.playerHome = playerHome;
	}

	public PlayerDto getPlayerAway() {
		return playerAway;
	}

	public void setPlayerAway(PlayerDto playerAway) {
		this.playerAway = playerAway;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Long groupNum) {
		this.groupNum = groupNum;
	}

	@Override
	public String toString() {
		return "GameDto [playerHome=" + playerHome + ", playerAway=" + playerAway + ", group=" + groupNum + ", round="
				+ round + "]";
	}

//	public CompetitionPlayer getCompetitionPlayer1() {
//		return this.competitionPlayer1;
//	}
//
//	public void setCompetitionPlayer1(CompetitionPlayer competitionPlayer1) {
//		this.competitionPlayer1 = competitionPlayer1;
//	}
//
//	public CompetitionPlayer getCompetitionPlayer2() {
//		return this.competitionPlayer2;
//	}
//
//	public void setCompetitionPlayer2(CompetitionPlayer competitionPlayer2) {
//		this.competitionPlayer2 = competitionPlayer2;
//	}

}