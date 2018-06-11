package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.model.Competition;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;
import com.ds.microservices.sport.tabletennis.service.GameService;
import com.ds.microservices.sport.tabletennis.service.PlayerService;;


@RequestMapping("/admin")
@RestController
public class CompetitionController {

	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
	@Autowired
	protected CompetitionService competitionService;
	
	@Autowired
	protected PlayerService playerService;
	
	@Autowired
	protected GameService gameService;
	
	@Autowired
	public CompetitionController(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@Autowired
	protected CompetitionConfiguration competitionConfiguration;
	

	
	// List of all competitions
	@RequestMapping(value="/competition", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionDto>> all() {
		logger.info("competition-controller all() invoked. Conf: (" + CompetitionConfiguration.NUMBER_OF_SEEDS + ")");

		return ResponseEntity.ok(competitionService.all());
	}
	
	// Find competition by id
	@RequestMapping(value="/competition/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findById(@PathVariable("id") Long id) {
		logger.info("competetion-controller findById() invoked: " + id);
		
		return ResponseEntity.ok(competitionService.findById(id));

	}
	
	// Save/update competition
	@RequestMapping(value="/competition/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<CompetitionDto> save(@RequestBody CompetitionDto competitionDto) {
		logger.info("competetion-controller save() invoked. ");
		
		return ResponseEntity.ok(competitionService.save(competitionDto));
	}


	// Find players-candidates for competition
	@RequestMapping(value="/competition/{id}/candidates", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PlayerDto>> findCandidatesForCompetition(@PathVariable Long id) {
		logger.info("player-controller findCandidatesForCompetition() invoked: " + id);
		
		return ResponseEntity.ok(playerService.findCandidatesForCompetition());

	}

	// Players from competition
	@RequestMapping(value="/competition/{id}/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Set<PlayerDto>> findPlayersForCompetition(@PathVariable Long id) {
		logger.info("game-controller findPlayersForCompetition() invoked: " + id);
		
		CompetitionDto competitionDto = competitionService.findById(id); 
		
		return ResponseEntity.ok(competitionDto.getPlayers());
	}

	// Games from competition
	@RequestMapping(value="/competition/{id}/games", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GameDto>> findGamesForCompetition(@PathVariable Long id) {
		logger.info("game-controller findGamesForCompetition() invoked: " + id);
		
		return ResponseEntity.ok(gameService.findGamesFromCompetition(id));
	}
	
	// Add player
	@RequestMapping("/competition/{id}/add/{playerId}")
	public ResponseEntity<CompetitionDto> addPlayerToCompetition(@PathVariable("id") Long competitionId, @PathVariable("playerId") Long playerId) {
		logger.info("competetion-controller findPlayers() invoked: " + competitionId + " : " + playerId);
		
		return ResponseEntity.ok(competitionService.addPlayerToCompetition(competitionId, playerId));

	}


	// Remove player
	@RequestMapping("/competition/{id}/remove/{playerId}")
	public ResponseEntity<CompetitionDto> removePlayerFromCompetition(@PathVariable("id") Long competitionId, @PathVariable("playerId") Long playerId) {
		logger.info("competetion-controller findPlayers() invoked: " + competitionId + " : " + playerId);
		
		return ResponseEntity.ok(competitionService.removePlayerFromCompetition(competitionId, playerId));

	}

	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping("/competition/{id}/generate")
	public ResponseEntity<Competition> generateCompetition(@PathVariable("id") Long competitionId) {
		logger.info("competetion-controller generateCompetition() invoked: " + competitionId);
		
		return ResponseEntity.ok(competitionService.generateCompetition(competitionId));

	}
	

}
