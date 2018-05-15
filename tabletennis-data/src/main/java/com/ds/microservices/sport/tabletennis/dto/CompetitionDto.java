package com.ds.microservices.sport.tabletennis.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Competition")
public class CompetitionDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String description;

	private String name;

	public CompetitionDto() {
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

}