package com.ds.microservices.sport.tabletennis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="competition_player")
@NamedQuery(name="CompetitionPlayer.findAll", query="SELECT c FROM CompetitionPlayer c")
public class CompetitionPlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompetitionPlayerPK id;

	private boolean seed;

	@Column(name="group_num")
	private Long groupNum;

	public CompetitionPlayer() {
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

	@Override
	public String toString() {
		return "CompetitionPlayer [id=" + id + ", seed=" + seed + ", groupNum=" + groupNum + "]";
	}


}