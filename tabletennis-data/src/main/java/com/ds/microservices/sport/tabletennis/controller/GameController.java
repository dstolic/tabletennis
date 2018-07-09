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
import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.service.BaseGameService;
import com.ds.microservices.sport.tabletennis.service.BasePlayerService;;


@RequestMapping("/admin")
@RestController
public class GameController {

	protected Logger logger = Logger.getLogger(GameController.class.getName());
	
	@Autowired
	protected BaseCompetitionService competitionService;
	
	@Autowired
	protected BasePlayerService playerService;
	
	@Autowired
	protected BaseGameService gameService;
	
	@Autowired
	protected GameMapper gameMapper;
	
	@Autowired
	public GameController(BaseCompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@Autowired
	protected CompetitionConfiguration competitionConfiguration;
	
	// Games from competition
	@RequestMapping(value="/competition/games", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findGamesForCompetition() {
		
		return ResponseEntity.ok(gameService.findGamesFromCompetition().stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}

	// Games from competition
	@RequestMapping(value="/competition/{id}/games", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findGamesForCompetition(@PathVariable Long id) {
		
		return ResponseEntity.ok(gameService.findGamesFromCompetition(id).stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}
	
	// Finished games
	@RequestMapping(value="/competition/games/finished", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findFinishedGames() {
		
		return ResponseEntity.ok(
				gameService.findFinishedGames()
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}
	
	// Finished games
	@RequestMapping(value="/competition/{id}/games/finished", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findFinishedGames(@PathVariable Long id) {
		logger.info("game-controller findGamesForCompetition() invoked.");
		
		return ResponseEntity.ok(
				gameService.findFinishedGames(id)
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}

	// Scheduled games
	@RequestMapping(value="/competition/games/scheduled", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findScheduledGames() {
		
		return ResponseEntity.ok(
				gameService.findScheduledGames()
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}
	
	// Scheduled games
	@RequestMapping(value="/competition/{id}/games/scheduled", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findScheduledGames(@PathVariable Long id) {
		
		return ResponseEntity.ok(
				gameService.findScheduledGames(id)
				.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}

	

}
