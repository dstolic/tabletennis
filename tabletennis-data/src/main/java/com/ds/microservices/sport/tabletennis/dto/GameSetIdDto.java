package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

public class GameSetIdDto {

	private Long gameId;

	private Long setNo;

	public Long getSetNo() {
		return setNo;
	}

	public void setSetNo(Long setNo) {
		this.setNo = setNo;
	}

	@Override
	public String toString() {
		return "GameSetId [game=" + gameId + ", setNo=" + setNo + "]";
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}


}