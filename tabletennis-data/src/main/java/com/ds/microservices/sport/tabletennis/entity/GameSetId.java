package com.ds.microservices.sport.tabletennis.entity;

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

@Embeddable
public class GameSetId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "game_id", insertable=false, updatable=false)
	private Game game;

	@Column(name="set_no")
	private Long setNo;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Long getSetNo() {
		return setNo;
	}

	public void setSetNo(Long setNo) {
		this.setNo = setNo;
	}

	@Override
	public String toString() {
		return "GameSetId [game=" + game + ", setNo=" + setNo + "]";
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CompetitionPlayerPK)) {
			return false;
		}
		GameSetId castOther = (GameSetId)other;
		return 
			this.game.getId().equals(castOther.getGame().getId())
			&& this.setNo == castOther.setNo;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.game.getId().hashCode();
		hash = hash * prime + this.setNo.hashCode();
		
		return hash;
	}


}