package com.ds.microservices.sport.tabletennis.report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@IdClass(CompetitionPlayerPK.class)
public class CompetitionPlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Long competitionId;
	protected Long playerId;

//	@EmbeddedId
	@Id
	private CompetitionPlayerPK id;

	//bi-directional many-to-one association to Competition
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "competition_id", insertable=true, updatable=false)
	private Competition competition;

	//bi-directional many-to-one association to Player
	@ManyToOne
    @JoinColumn(name = "player_id", insertable=true, updatable=false)
	private Player player;

//	private Player player;
	
	private boolean seed;

	@Column(name="group_num")
	private Long groupNum;

	public CompetitionPlayer() {
	}
	
	public CompetitionPlayer(Player player, boolean seed) {
		this.player = player;
		this.seed = seed;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}

	public Long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Long groupNum) {
		this.groupNum = groupNum;
	}

	public CompetitionPlayerPK getId() {
		return id;
	}

	public void setId(CompetitionPlayerPK id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@Override
	public String toString() {
		return "CompetitionPlayer [id=" + id + ", seed=" + seed + ", groupNum=" + groupNum + "]";
	}


}