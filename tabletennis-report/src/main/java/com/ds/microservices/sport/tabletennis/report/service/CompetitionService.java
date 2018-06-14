package com.ds.microservices.sport.tabletennis.report.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;

@Service
public class CompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());
	
	@Autowired
	protected CompetitionRepository competitionRepository;

	// Find competition by id
	public Competition findById(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(id).get();
	}

	public Competition findByIdNoTransform(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(id).get();
	}

	public Competition findByCurrent() {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findByCurrent(true);
	}
}
