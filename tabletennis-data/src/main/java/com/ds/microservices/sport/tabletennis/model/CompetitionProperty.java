package com.ds.microservices.sport.tabletennis.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the competition_properties database table.
 * 
 */
@Entity
@Table(name="competition_properties")
@NamedQuery(name="CompetitionProperty.findAll", query="SELECT c FROM CompetitionProperty c")
public class CompetitionProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="competition_id")
	private BigInteger competitionId;

	private String name;

	private String value;

	public CompetitionProperty() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getCompetitionId() {
		return this.competitionId;
	}

	public void setCompetitionId(BigInteger competitionId) {
		this.competitionId = competitionId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}