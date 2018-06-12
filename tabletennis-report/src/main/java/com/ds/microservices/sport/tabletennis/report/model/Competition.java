package com.ds.microservices.sport.tabletennis.report.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="competition")
@NamedQuery(name="Competition.findAll", query="SELECT c FROM Competition c")
public class Competition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	private String name;

	private boolean completed;

//    @ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="competition_player", 
//			joinColumns = @JoinColumn(name="competition_id", referencedColumnName="id"),
//			inverseJoinColumns = @JoinColumn (name="player_id", referencedColumnName="id")
//	)
	@Transient
	private Set<Player> players;

	@OneToMany
	@JoinColumn(name="competition_id")
    private Set<CompetitionProperty> properties;
	
	@OneToMany(mappedBy="competition", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Game> games;

	@OneToMany(mappedBy="competition")
	private List<CompetitionPlayer> competitionPlayers;
    
	@Transient
	private Map<String, CompetitionProperty> propertiesMap;
	
	@Transient
	private Map<CompetitionPlayerPK, CompetitionPlayer> competitionPlayerMap;
    
	@Transient
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

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player player) {
		if (players == null) {
			players = new HashSet<Player>();
		}
		players.add(player);
	}
	
	public Set<CompetitionProperty> getProperties() {
		return properties;
	}

	public void setProperties(Set<CompetitionProperty> properties) {
		this.properties = properties;
	}

	public Map<String, CompetitionProperty> getPropertiesMap() {
		if(propertiesMap == null) {
			propertiesMap = new ConcurrentHashMap<>();
			if(this.getProperties() != null) {
				this.getProperties().forEach(prop -> propertiesMap.put(prop.getName(), prop));
			}
		}
		return propertiesMap;
	}

	public void setPropertiesMap(Map<String, CompetitionProperty> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	public Map<CompetitionPlayerPK, CompetitionPlayer> getCompetitionPlayersMap() {
		if(competitionPlayerMap == null) {
			competitionPlayerMap = new ConcurrentHashMap<>();
			if(this.getCompetitionPlayers() != null) {
				this.getCompetitionPlayers().forEach(cp -> competitionPlayerMap.put(cp.getId(), cp));
			}
		}
		return competitionPlayerMap;
	}

	public void setCompetitionPlayersMap(Map<CompetitionPlayerPK, CompetitionPlayer> competitionPlayerMap) {
		this.competitionPlayerMap = competitionPlayerMap;
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

	@Override
	public String toString() {
		return "Competition [id=" + id + ", description=" + description + ", name=" + name + ", players=" + players
				+ "]";
	}

}