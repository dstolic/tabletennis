package com.ds.microservices.sport.tabletennis.report.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class CompetitionPlayerDto implements Serializable {
	private static final long serialVersionUID = 1L;

//	@JsonIgnoreProperties({"competition", "player"})
	private CompetitionPlayerPKDto id;

	private boolean seed;
	
	private boolean active;

	public CompetitionPlayerDto() {
	}

	public CompetitionPlayerPKDto getId() {
		return id;
	}

	public void setId(CompetitionPlayerPKDto id) {
		this.id = id;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}