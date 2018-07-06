package com.ds.microservices.sport.tabletennis.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
//	@JsonIgnore
//	private List<CompetitionPlayer> competitionPlayers;

//	@JoinTable(name="competition_player", 
//	joinColumns = @JoinColumn(name="competition_id", referencedColumnName="id"),
//	inverseJoinColumns = @JoinColumn (name="player_id", referencedColumnName="id")
//	)

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="group_player",
		joinColumns = @JoinColumn(name="group_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn (name="player_id", referencedColumnName="id")
	)
	private List<Player> players;
	
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

	public int groupPoints() {
		int sum = 0;
		Long maxPoints = 0l;

		if (players != null) {
			for (Player player : players) {
				sum += player.getPoints();
				if(maxPoints < player.getPoints()) {
					maxPoints = player.getPoints();
				}
			}			
		}
		
		return sum - maxPoints.intValue();
	}

//	public List<CompetitionPlayer> getCompetitionPlayers() {
//		return competitionPlayers;
//	}
//
//	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
//		this.competitionPlayers = competitionPlayers;
//	}

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

	@Override
	public String toString() {
		return "Group [id=" + id + ", points=" + groupPoints() + ", players=" + players + "]";
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}