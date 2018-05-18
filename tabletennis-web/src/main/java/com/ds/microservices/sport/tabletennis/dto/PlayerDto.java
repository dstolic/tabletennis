package com.ds.microservices.sport.tabletennis.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Player")
public class PlayerDto {

	private Long id;

	private boolean active;

	private String firstName;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}