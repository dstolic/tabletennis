package com.ds.microservices.sport.tabletennis.report.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.ds.microservices.sport.tabletennis.report.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.report.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.report.dto.CompetitionPlayerDto;
import com.ds.microservices.sport.tabletennis.report.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.report.mapper.CompetitionPlayerMapper;
import com.ds.microservices.sport.tabletennis.report.mapper.CompetitionMapper;
import com.ds.microservices.sport.tabletennis.report.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.report.service.impl.CompetitionService;;


@RestController
public class CompetitionController {

	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
	@Autowired
	protected CompetitionService competitionService;
	
	@Autowired
	protected CompetitionMapper competitionMapper;

	@Autowired
	protected CompetitionPlayerMapper competitionPlayerMapper;

	
	// Find current competition
	@RequestMapping(value="/competition", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findCurrentCompetition() {
		logger.info("competetion-controller findCurrentCompetition() start");
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.findByCurrent(), new CycleAvoidMappingContext()
					));

	}

	// Players from competition
	@RequestMapping(value="/competition/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionPlayerDto>> findPlayersFromCompetition() {
		logger.info("player-controller findPlayersFromCompetition() invoked.");
		
		return ResponseEntity.ok(
				competitionService.findPlayersFromCompetition()
				.stream()
				.map(player -> competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));

	}
	


}
