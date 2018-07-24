package com.ds.microservices.sport.tabletennis.report.dto;

public class GameSetIdDto {

	private Long gameId;

	private Long setNo;

	public Long getSetNo() {
		return setNo;
	}

	public void setSetNo(Long setNo) {
		this.setNo = setNo;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		return "GameSetId [game=" + gameId + ", setNo=" + setNo + "]";
	}


}