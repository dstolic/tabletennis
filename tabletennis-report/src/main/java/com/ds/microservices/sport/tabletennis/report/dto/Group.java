package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Group")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private List<CompetitionPlayerDto> competitionPlayers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	@Override
//	public String toString() {
////		return "Group [id=" + id + ", players=" + competitionPlayers + "]";
//		return "Group [id=" + id + ", points=" + groupPoints() + ", players=" + competitionPlayers + "]";
//	}

	// for testing
	public int groupPoints() {
		int sum = 0;
		Long maxPoints = 0l;

		if (competitionPlayers != null) {
			for (CompetitionPlayerDto competitionPlayerDto : competitionPlayers) {
				PlayerDto playerDto = competitionPlayerDto.getId().getPlayer();
				sum += playerDto.getPoints();
				if(maxPoints < playerDto.getPoints()) {
					maxPoints = playerDto.getPoints();
				}
			}			
		}
		
		return sum - maxPoints.intValue();
	}

}