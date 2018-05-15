package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.sql.Timestamp;
import java.util.List;

@JsonRootName("Player")
public class PlayerDto implements Serializable {
	@Id
	private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private BigInteger points;

	public PlayerDto() {
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

	public BigInteger getPoints() {
		return this.points;
	}

	public void setPoints(BigInteger points) {
		this.points = points;
	}

}