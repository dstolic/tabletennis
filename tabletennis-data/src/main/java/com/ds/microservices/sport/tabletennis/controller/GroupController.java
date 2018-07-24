package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.dto.GroupDto;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.GroupMapper;
import com.ds.microservices.sport.tabletennis.service.impl.GroupService;

@RequestMapping("/admin")
@RestController
public class GroupController {

	protected Logger logger = Logger.getLogger(GroupController.class.getName());
	
	@Autowired
	protected GroupService groupService;
	
	@Autowired
	protected GroupMapper groupMapper;

	// Find groups 
	@RequestMapping(value="/competition/{id}/group", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<List<GroupDto>> findGroups(@PathVariable Long id) {
		return ResponseEntity.ok(
				groupMapper.groupToGroupDto(
						groupService.findAllByCompetition(id), new CycleAvoidMappingContext()
					));
	}

	// Find groups 
	@RequestMapping(value="/competition/group", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<List<GroupDto>> findGroups() {
		return ResponseEntity.ok(
				groupMapper.groupToGroupDto(
						groupService.findAllByCompetition(), new CycleAvoidMappingContext()
					));
	}

	// Find groups 
	@RequestMapping(value="/competition/{id}/group/{name}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<GroupDto> findGroupByName(@PathVariable Long id, @PathVariable String name) {
		return ResponseEntity.ok(
				groupMapper.groupToGroupDto(
						groupService.findByName(id, name), new CycleAvoidMappingContext()
					));
	}

	// Find groups 
	@RequestMapping(value="/competition/group/{name}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<GroupDto> findGroupByName(@PathVariable String name) {
		return ResponseEntity.ok(
				groupMapper.groupToGroupDto(
						groupService.findByName(name), new CycleAvoidMappingContext()
					));
	}
}
