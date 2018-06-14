package com.ds.microservices.sport.tabletennis.report.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.ds.microservices.sport.tabletennis.report.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.report.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.report.mapper.CompetititonMapper;
import com.ds.microservices.sport.tabletennis.report.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.report.service.CompetitionService;;


@RestController
public class CompetitionController {

	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
	@Autowired
	protected CompetitionService competitionService;
	
	@Autowired
	protected CompetititonMapper competitionMapper;

	
	// Find current competition
	@RequestMapping(value="/competition", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findCurrentCompetition() {
		logger.info("competetion-controller findCurrentCompetition() start");
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.findByCurrent(), new CycleAvoidMappingContext()
					));

	}
	
}
