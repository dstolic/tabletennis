package com.ds.microservices.sport.tabletennis.report.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="competition_player")
@NamedQuery(name="CompetitionPlayer.findAll", query="SELECT c FROM CompetitionPlayer c")
public class CompetitionPlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonIgnoreProperties({"competition", "player"})
	private CompetitionPlayerPK id;

	private boolean seed;

	private boolean active;

	public CompetitionPlayer() {
	}
	
	public CompetitionPlayer(Competition competition, Player player) {
		this.id = new CompetitionPlayerPK(competition, player);
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}

	public CompetitionPlayerPK getId() {
		return id;
	}

	public void setId(CompetitionPlayerPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CompetitionPlayer [id=" + id + ", seed=" + seed +  "]";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}