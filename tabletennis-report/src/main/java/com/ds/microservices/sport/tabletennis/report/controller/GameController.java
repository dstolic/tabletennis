package com.ds.microservices.sport.tabletennis.report.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.report.dto.GameDto;
import com.ds.microservices.sport.tabletennis.report.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.report.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.report.service.BaseGameService;;


@RestController
public class GameController {

	protected Logger logger = Logger.getLogger(GameController.class.getName());
	
	@Autowired
	protected BaseGameService gameService;
	
	@Autowired
	protected GameMapper gameMapper;
	
	@Autowired
	public GameController() {
	}


	// Games from competition
	@RequestMapping(value="/competition/games", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findGamesForCompetition() {
		logger.info("game-controller findGamesForCompetition() invoked.");
		
		return ResponseEntity.ok(
				gameService.findGamesFromCompetition()
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));

	}
	
	// Finished games
	@RequestMapping(value="/competition/games/finished", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findFinishedGames() {
		logger.info("game-controller findGamesForCompetition() invoked.");
		
		return ResponseEntity.ok(
				gameService.findFinishedGames()
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));

	}
	
	// Scheduled games
	@RequestMapping(value="/competition/games/scheduled", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findScheduledGames() {
		logger.info("game-controller findGamesForCompetition() invoked.");
		
		return ResponseEntity.ok(
				gameService.findScheduledGames()
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));

	}
	


}
