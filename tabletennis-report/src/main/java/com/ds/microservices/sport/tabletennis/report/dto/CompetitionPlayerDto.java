package com.ds.microservices.sport.tabletennis.report.dto;

public class CompetitionPlayerDto {

	private CompetitionPlayerPKDto id;

	private boolean seed;
	
	private boolean active;

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