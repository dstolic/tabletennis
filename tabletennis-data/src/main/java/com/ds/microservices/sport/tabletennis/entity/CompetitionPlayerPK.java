package com.ds.microservices.sport.tabletennis.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class CompetitionPlayerPK implements Serializable {
	private static final long serialVersionUID = 1L;

//	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "competition_id", insertable=false, updatable=false)
	private Competition competition;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "player_id", insertable=false, updatable=false)
	private Player player;

	public CompetitionPlayerPK() {
	}

	public CompetitionPlayerPK(Competition competition, Player player) {
		super();
		this.competition = competition;
		this.player = player;
	}
	
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CompetitionPlayerPK)) {
			return false;
		}
		CompetitionPlayerPK castOther = (CompetitionPlayerPK)other;
		return 
			this.competition.getId().equals(castOther.getCompetition().getId())
			&& this.player.getId().equals(castOther.getPlayer().getId());
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.competition.getId().hashCode();
		hash = hash * prime + this.player.getId().hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "CompetitionPlayerPK [competitionId=" + this.competition.getId() + ", playerId=" + this.player.getId() + "]";
	}
}