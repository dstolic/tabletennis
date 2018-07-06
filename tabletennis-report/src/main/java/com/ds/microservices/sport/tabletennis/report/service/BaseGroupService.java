package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Group;

public interface BaseGroupService {

	// List of all competitions
	public List<Group> findByCompetition();

	// Find competition by id
	Group findById(Long id);

	

}