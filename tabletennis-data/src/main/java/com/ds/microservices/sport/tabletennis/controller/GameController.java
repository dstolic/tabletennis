package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	// Game from competition
	@RequestMapping(value="/competition/games/{gameid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameDto> findGameForCompetition(@PathVariable Long gameid) {
		return ResponseEntity.ok(
				gameMapper.gameToGameDto(
						gameService.findGameFromCompetition(gameid), new CycleAvoidMappingContext()
				));
	}
	

	// Game from competition
	@RequestMapping(value="/competition/{id}/games/{gameid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameDto> findGameForCompetition(@PathVariable Long id, @PathVariable Long gameid) {
		return ResponseEntity.ok(
				gameMapper.gameToGameDto(
						gameService.findGameFromCompetition(id, gameid), new CycleAvoidMappingContext()
				));
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


/*
 *  Edit functions
 */
	// Get game 
	@RequestMapping(value="/competition/game/{gameid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<GameDto> getGame(@PathVariable("gameid") Long id) {
		return ResponseEntity.ok(
				gameMapper.gameToGameDto(
						gameService.getGame(id), 
						new CycleAvoidMappingContext()
				));
	}

	// Add game result
	@RequestMapping(value="/competition/game/{gameid}/result", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<GameDto> addGameResult(@PathVariable("gameid") Long id, @RequestBody GameDto gameDto) {
		return ResponseEntity.ok(
				gameMapper.gameToGameDto(
						gameService.addGameResult(id, 
								gameMapper.gameDtoToGame(gameDto, new CycleAvoidMappingContext())
								)
						, new CycleAvoidMappingContext()
				));
	}

	

}
