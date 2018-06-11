package com.ds.microservices.sport.tabletennis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

//	private Long competitionId;

	private Long id;

	private List<CompetitionPlayer> competitionPlayers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", points=" + groupPoints() + ", players=" + competitionPlayers + "]";
	}

	public int groupPoints() {
		int sum = 0;
		Long maxPoints = 0l;

		if (competitionPlayers != null) {
			for (CompetitionPlayer competitionPlayer : competitionPlayers) {
				sum += competitionPlayer.getPlayer().getPoints();
				if(maxPoints < competitionPlayer.getPlayer().getPoints()) {
					maxPoints = competitionPlayer.getPlayer().getPoints();
				}
			}			
		}
		
		return sum - maxPoints.intValue();
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

}