package com.ds.microservices.sport.tabletennis.report.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


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
	private Long id;

	private boolean active;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private Long points;
	
//	private boolean seed;

	//bi-directional many-to-one association to CompetitionPlayer
//	@OneToMany(mappedBy="player")
//    @ManyToOne
//    @JoinColumn(name = "competition_id")
//	private Competition competition;

	public Player() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


//	public boolean isSeed() {
//		return seed;
//	}
//
//	public void setSeed(boolean seed) {
//		this.seed = seed;
//	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", points=" + points + "]";
	}

//	@Override
//	public int compareTo(Player o) {
//		if(this.points > o.getPoints()) return -1;
//		else if (this.points > o.getPoints()) return 1;
//		
//		return 0;
//	}
//
}