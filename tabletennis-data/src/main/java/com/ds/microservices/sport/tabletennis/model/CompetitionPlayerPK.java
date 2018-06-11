package com.ds.microservices.sport.tabletennis.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the competition_player database table.
 * 
 */
@Embeddable
public class CompetitionPlayerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="competition_id", insertable=false, updatable=false)
	private Long competitionId;

	@Column(name="player_id", insertable=false, updatable=false)
	private Long playerId;

	public CompetitionPlayerPK() {
	}

	public CompetitionPlayerPK(Long competitionId, Long playerId) {
		super();
		this.competitionId = competitionId;
		this.playerId = playerId;
	}
	
	public Long getCompetitionId() {
		return this.competitionId;
	}
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	public Long getPlayerId() {
		return this.playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
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
			this.competitionId.equals(castOther.competitionId)
			&& this.playerId.equals(castOther.playerId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.competitionId.hashCode();
		hash = hash * prime + this.playerId.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "CompetitionPlayerPK [competitionId=" + competitionId + ", playerId=" + playerId + "]";
	}
}