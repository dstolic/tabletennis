package com.ds.microservices.sport.tabletennis.report.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="competition")
@NamedQuery(name="Competition.findAll", query="SELECT c FROM Competition c")
public class Competition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String category;

	private String description;

	private String name;

	private boolean completed;

	private boolean current;

//    @ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="competition_player", 
//			joinColumns = @JoinColumn(name="competition_id", referencedColumnName="id"),
//			inverseJoinColumns = @JoinColumn (name="player_id", referencedColumnName="id")
//	)

//	@JoinColumn(name="competition_id")
	@OneToMany(mappedBy="competition")
    private Set<CompetitionProperty> properties;
	
	@OneToMany(mappedBy="competition", cascade=CascadeType.ALL)
	private List<Game> games;

	@OneToMany(mappedBy="id.competition")
	private List<CompetitionPlayer> competitionPlayers;

	@OneToMany(mappedBy="competition", cascade=CascadeType.ALL)
//	@Transient
	private List<Group> groups;
    
	public Competition() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CompetitionProperty> getProperties() {
		return properties;
	}

	public void setProperties(Set<CompetitionProperty> properties) {
		this.properties = properties;
	}

    public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", description=" + description + ", name=" + name + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


}