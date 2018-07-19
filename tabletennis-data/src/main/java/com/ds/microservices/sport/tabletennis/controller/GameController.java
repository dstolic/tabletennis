package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.dto.GameSetDto;
import com.ds.microservices.sport.tabletennis.dto.GameSetIdDto;
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


/*
 *  Edit functions
 */
	// Get game 
	@RequestMapping(value="/competition/game/{gameid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<GameDto> getGame(@PathVariable("gameid") Long id) {
		logger.log(Level.INFO, "competetion-controller getGame() invoked: {0}", id );
		
		return ResponseEntity.ok(
				gameMapper.gameToGameDto(
						gameService.getGame(id), 
						new CycleAvoidMappingContext()
				));
	}

	// Add game result
	@RequestMapping(value="/competition/game/{gameid}/result", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<GameDto> addGameResult(@PathVariable("gameid") Long id, @RequestBody GameDto gameDto) {
		logger.log(Level.INFO, "competetion-controller addGameResult() invoked: {0}", id );
		
		
		GameDto game = new GameDto();

		int[] homeGamePoints = {2, 11, 11, 5, 11};
		int[] awayGamePoints = {11, 5, 6, 11, 0};
		
		int homeWinSets = 0;
		int awayWinSets = 0;
		
		for (int i = 0; i < awayGamePoints.length; i++) {
			GameSetDto gameSet = new GameSetDto();
			GameSetIdDto gameSetId = new GameSetIdDto();
			gameSetId.setGameId(id);
			gameSetId.setSetNo(new Long(i+1));
			
			gameSet.setId(gameSetId);
			gameSet.setPointsHome(homeGamePoints[i]);
			gameSet.setPointsAway(awayGamePoints[i]);
			
//			gameSetRepository.save(gameSet);
			game.getSets().add(gameSet);
			
			if (homeGamePoints[i] > awayGamePoints[i]) {
				homeWinSets++;
			} else {
				awayWinSets++;
			}
		}
		game.setPointsHome(homeWinSets);
		game.setPointsAway(awayWinSets);
		
		
		return ResponseEntity.ok(
				gameMapper.gameToGameDto(
						gameService.addGameResult(id, game), 
						new CycleAvoidMappingContext()
				));
	}

	

}
