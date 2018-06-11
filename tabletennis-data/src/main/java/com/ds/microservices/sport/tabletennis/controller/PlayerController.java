package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;
import com.ds.microservices.sport.tabletennis.service.PlayerService;

@RequestMapping("/admin")
@RestController
public class PlayerController {

	protected Logger logger = Logger.getLogger(PlayerController.class.getName());
	
	protected PlayerRepository playerRepository;

	protected PlayerService playerService;

	protected CompetitionService competitionService;
	
	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}


	// List of all players
	@RequestMapping(value="/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PlayerDto>> all() {
		logger.info("player-controller findPlayers() invoked.");
		
		return ResponseEntity.ok(playerService.all());
	}

	// Find player by id
	@RequestMapping(value="/players/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayerDto> findById(@PathVariable("id") Long id) {
		logger.info("player-controller findById() invoked: " + id);
		
		return ResponseEntity.ok(playerMapper.playerToPlayerDto(playerService.findOne(id), new CycleAvoidMappingContext()));
	}
	

	// Save/update player
	@RequestMapping(value="/players/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayerDto> save(@RequestBody PlayerDto playerDto) {
		logger.info("player-controller save() invoked. ");
		
		return ResponseEntity.ok(playerService.save(playerDto));
	}

	// Activate/deactivate player
	@RequestMapping(value="/players/activate", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayerDto> activatePlayers(@RequestParam Long id, @RequestParam boolean active) {
		logger.info("player-controller activatePlayer() invoked. ");
		
		return ResponseEntity.ok(playerService.activatePlayer(id, active));
	}


}
