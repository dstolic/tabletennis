package com.ds.microservices.sport.tabletennis.report.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="competition_group")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

//	@OneToMany(mappedBy="group")
	@JsonIgnore
	@Transient
	private List<CompetitionPlayer> competitionPlayers;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="competition_id")
	@JsonIgnore
	private Competition competition;
	
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

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

}