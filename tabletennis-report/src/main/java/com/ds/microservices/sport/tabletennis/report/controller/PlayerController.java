package com.ds.microservices.sport.tabletennis.report.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.report.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.report.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.report.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.report.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.report.service.BasePlayerService;

@RestController
public class PlayerController {

	protected Logger logger = Logger.getLogger(PlayerController.class.getName());
	
	protected BasePlayerService playerService;

	protected BaseCompetitionService competitionService;
	
	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	public PlayerController(BasePlayerService playerService) {
		this.playerService = playerService;
	}


	// List of all players
	@RequestMapping(value="/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PlayerDto>> allPlayers() {
		logger.info("player-controller findPlayers() invoked.");
		
		return ResponseEntity.ok(
				playerService.allPlayers()
				.stream()
				.map(player -> playerMapper.playerToPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList())				
				);
	}

	// Find player by id
	@RequestMapping(value="/players/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayerDto> findById(@PathVariable("id") Long id) {
		logger.info("player-controller findById() invoked: " + id);
		
		return ResponseEntity.ok(playerMapper.playerToPlayerDto(playerService.findOne(id), new CycleAvoidMappingContext()));
	}
	

}
