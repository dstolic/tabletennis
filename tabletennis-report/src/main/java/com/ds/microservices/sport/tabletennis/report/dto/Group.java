package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Group")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private List<PlayerDto> players;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDto> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", points=" + groupPoints() + ", players=" + players + "]";
	}

	// for testing
	public int groupPoints() {
		int sum = 0;
		Long maxPoints = 0l;

		if (players != null) {
			for (PlayerDto player : players) {
				sum += player.getPoints();
				if(maxPoints < player.getPoints()) {
					maxPoints = player.getPoints();
				}
			}			
		}
		
		return sum - maxPoints.intValue();
	}

}