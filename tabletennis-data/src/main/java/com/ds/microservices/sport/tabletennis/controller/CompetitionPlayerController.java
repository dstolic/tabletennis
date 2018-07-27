package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.dto.CompetitionPlayerDto;
import com.ds.microservices.sport.tabletennis.mapper.CompetitionPlayerMapper;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionPlayerService;


@RequestMapping("/admin")
@RestController
public class CompetitionPlayerController {

	protected Logger logger = Logger.getLogger(CompetitionPlayerController.class.getName());
	
// Configuration
	@Autowired
	protected CompetitionConfiguration competitionConfiguration;

// Services
	@Autowired
	protected BaseCompetitionPlayerService competitionPlayerService;
	
// Mappers
	@Autowired
	protected CompetitionPlayerMapper competitionPlayerMapper;

/* 
 *  Common functions 
 */
	
	// Players from competition
	@RequestMapping(value="/competition/{id}/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionPlayerDto>> findPlayersFromCompetition(@PathVariable Long id) {
		return ResponseEntity.ok(
				competitionPlayerService.findPlayersFromCompetition(id)
				.stream()
				.map(player -> competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}

	// Players from current competition
	@RequestMapping(value="/competition/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionPlayerDto>> findPlayersFromCompetition() {
		return ResponseEntity.ok(
				competitionPlayerService.findPlayersFromCompetition()
				.stream()
				.map(player -> competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}
	
	
	// Player from competition
	@RequestMapping(value="/competition/{id}/players/{playerid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CompetitionPlayerDto> findPlayerFromCompetition(@PathVariable Long id, @PathVariable Long playerid) {
		return ResponseEntity.ok(
				competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(
						competitionPlayerService.findPlayerFromCompetition(id, playerid), 
						new CycleAvoidMappingContext() 
						));
	}
	

	// Player from current competition
	@RequestMapping(value="/competition/players/{playerid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CompetitionPlayerDto> findPlayerFromCompetition(@PathVariable Long playerid) {
		return ResponseEntity.ok(
				competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(
						competitionPlayerService.findPlayerFromCompetition(playerid), 
						new CycleAvoidMappingContext() 
						));
	}
	

}
