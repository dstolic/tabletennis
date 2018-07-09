package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Group;

public interface BaseGroupService {

	// List of all competitions
	public List<Group> findAllByCompetition();
	public List<Group> findAllByCompetition(Long competitionId);

	// Find group by id
	Group findById(Long id);
	
	// Find group by name
	Group findByName(String name);
	Group findByName(Long competitionId, String name);
	

	

}