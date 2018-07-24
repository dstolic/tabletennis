package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Group;

public interface BaseGroupService {

	public List<Group> findAllByCompetition();

	Group findById(Long id);
	
	Group findByName(String name);
	

	

}