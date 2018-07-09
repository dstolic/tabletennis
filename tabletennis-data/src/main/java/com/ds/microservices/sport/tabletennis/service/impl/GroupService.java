package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.Group;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.repository.GroupRepository;
import com.ds.microservices.sport.tabletennis.service.BaseGroupService;

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
	public List<Group> findAllByCompetition() {
		Competition competition =  competitionRepository.findByCurrent(true);
		return groupRepository.findByCompetitionId(competition.getId());
	}

	@Override
	public List<Group> findAllByCompetition(Long competitionId) {
		return groupRepository.findByCompetitionId(competitionId);
	}

	@Override
	public Group findByName(String name) {
		Competition competition =  competitionRepository.findByCurrent(true);
		return groupRepository.findByCompetitionIdAndName(competition.getId(), name);
	}

	@Override
	public Group findByName(Long competitionId, String name) {

		return groupRepository.findByCompetitionIdAndName(competitionId, name);
	}

}
