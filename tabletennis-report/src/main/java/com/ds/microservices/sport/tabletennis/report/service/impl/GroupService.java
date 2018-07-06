package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.Group;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.GroupRepository;
import com.ds.microservices.sport.tabletennis.report.service.BaseGroupService;

@Service
public class GroupService implements BaseGroupService {

	protected Logger logger = Logger.getLogger(GroupService.class.getName());
	
	private final GroupRepository groupRepository;
	
	private final CompetitionRepository competitionRepository;

	@Autowired
	public GroupService(GroupRepository groupRepository, CompetitionRepository competitionRepository) {
		this.groupRepository = groupRepository;
		this.competitionRepository = competitionRepository;
	}

	// Find competition by id
	@Override
	public Group findById(Long id) {
		logger.info("competition-service findById invoked. ");
	
//		return competitionRepository.findById(id).get();
		return null;
	}

	@Override
	public List<Group> findByCompetition() {
		Competition competition =  competitionRepository.findByCurrent(true);

		return groupRepository.findByCompetitionId(competition.getId());
	}

}