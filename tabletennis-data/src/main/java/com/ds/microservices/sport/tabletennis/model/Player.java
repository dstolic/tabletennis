package com.ds.microservices.sport.tabletennis.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the player database table.
 * 
 */
@Entity
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private boolean active;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private BigInteger points;

	//bi-directional many-to-one association to CompetitionPlayer
	@OneToMany(mappedBy="player")
	private List<CompetitionPlayer> competitionPlayers;

	public Player() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigInteger getPoints() {
		return this.points;
	}

	public void setPoints(BigInteger points) {
		this.points = points;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

}