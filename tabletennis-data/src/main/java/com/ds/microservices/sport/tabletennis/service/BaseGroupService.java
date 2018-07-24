package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Group;

public interface BaseGroupService {

	public List<Group> findAllByCompetition();
	public List<Group> findAllByCompetition(Long competitionId);

	Group findById(Long id);
	
	Group findByName(String name);
	Group findByName(Long competitionId, String name);
	

}