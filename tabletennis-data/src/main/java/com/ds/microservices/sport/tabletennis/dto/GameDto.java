package com.ds.microservices.sport.tabletennis.dto;

import java.util.List;

import com.ds.microservices.sport.tabletennis.dto.GameSetDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Game")
public class GameDto {

	private Long id;

	private int finished;

	private GameStatus finishedStatus = GameStatus.SCHEDULED;

	private int pointsAway;

	private int pointsHome;

	@JsonIgnore
	private CompetitionDto competition;

	private PlayerDto playerHome;

	private PlayerDto playerAway;

	private Long groupId;

	private int round;

	private List<GameSetDto> sets;
	
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public GameStatus getFinishedStatus() {
		return finishedStatus;
	}

	public void setFinishedStatus(GameStatus finishedStatus) {
		this.finishedStatus = finishedStatus;
	}

	public List<GameSetDto> getSets() {
		return sets;
	}

	public void setSets(List<GameSetDto> sets) {
		this.sets = sets;
	}

	@Override
	public String toString() {
		return "GameDto [playerHome=" + playerHome + ", playerAway=" + playerAway + ", group=" + groupId + ", round=" + round + ", groupId=" + groupId +"]";
	}


}