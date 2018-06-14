package com.ds.microservices.sport.tabletennis.report.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="competition_properties")
@NamedQuery(name="CompetitionProperty.findAll", query="SELECT c FROM CompetitionProperty c")
public class CompetitionProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@JsonIgnore // budzevina, resiti kasnije
//	@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "competition_id")
	private Competition competition;

	private String name;

	private String value;

	public CompetitionProperty() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return this.competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
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

	@Override
	public String toString() {
		return "CompetitionProperty [id=" + id + ", competition=" + competition + ", name=" + name + ", value=" + value + "]";
	}

}