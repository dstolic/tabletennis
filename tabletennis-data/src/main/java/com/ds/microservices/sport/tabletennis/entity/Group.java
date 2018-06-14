package com.ds.microservices.sport.tabletennis.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private List<CompetitionPlayer> competitionPlayers;
	
	private String name;
	
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
				sum += competitionPlayer.getId().getPlayer().getPoints();
				if(maxPoints < competitionPlayer.getId().getPlayer().getPoints()) {
					maxPoints = competitionPlayer.getId().getPlayer().getPoints();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}