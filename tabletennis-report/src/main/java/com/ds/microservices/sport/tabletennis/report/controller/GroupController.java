package com.ds.microservices.sport.tabletennis.report.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.report.dto.GroupDto;
import com.ds.microservices.sport.tabletennis.report.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.report.mapper.GroupMapper;
import com.ds.microservices.sport.tabletennis.report.service.impl.GroupService;;


@RestController
public class GroupController {

	protected Logger logger = Logger.getLogger(GroupController.class.getName());
	
	@Autowired
	protected GroupService groupService;
	
	@Autowired
	protected GroupMapper groupMapper;

//	@Autowired
//	protected CompetitionPlayerMapper competitionPlayerMapper;

	
	// Find groups 
	@RequestMapping(value="/competition/group", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<List<GroupDto>> findGroups() {
		logger.info("competetion-controller findGroups() start");
		
		return ResponseEntity.ok(
				groupMapper.groupToGroupDto(
						groupService.findAllByCompetition(), new CycleAvoidMappingContext()
					));

	}

	// Find groups 
	@RequestMapping(value="/competition/group/{name}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<GroupDto> findGroupByName(@PathVariable String name) {
		logger.info("competetion-controller findGroupByName() start");
		
		return ResponseEntity.ok(
				groupMapper.groupToGroupDto(
						groupService.findByName(name), new CycleAvoidMappingContext()
					));

	}

}
