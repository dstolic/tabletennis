package com.ds.microservices.sport.tabletennis.report.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.mapper.CompetititonMapper;
import com.ds.microservices.sport.tabletennis.report.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.report.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.report.model.Competition;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;

@Service
public class CompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());
	
	@Autowired
	protected CompetitionRepository competitionRepository;

	@Autowired
	private CompetititonMapper competitionMapper;



	// Find competition by id
	public CompetitionDto findById(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionMapper.competitionToCompetitionDto(competitionRepository.findById(id).get(), new CycleAvoidMappingContext());
	}

	public Competition findByIdNoTransform(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(id).get();
	}

}
