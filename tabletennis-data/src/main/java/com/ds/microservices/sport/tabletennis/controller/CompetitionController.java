package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.CompetitionPlayerDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.mapper.CompetitionPlayerMapper;
import com.ds.microservices.sport.tabletennis.mapper.CompetitionMapper;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.service.BaseGameService;
import com.ds.microservices.sport.tabletennis.service.BasePlayerService;;


@RequestMapping("/admin")
@RestController
public class CompetitionController {

	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
	@Autowired
	protected BaseCompetitionService competitionService;
	
	@Autowired
	protected BasePlayerService playerService;
	
	@Autowired
	protected BaseGameService gameService;
	
	@Autowired
	private CompetitionMapper competitionMapper;

	@Autowired
	private GameMapper gameMapper;

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	protected CompetitionConfiguration competitionConfiguration;

	@Autowired
	protected CompetitionPlayerMapper competitionPlayerMapper;


/* 
 *  Common functions 
 */
		
	// List of all competitions
	@RequestMapping(value="/competitions", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionDto>> allCompetitions() {
		logger.info("competition-controller allCompetitions() invoked.");

		return ResponseEntity.ok(competitionService.allCompetitions()
				.stream()
				.map(competition -> competitionMapper.competitionToCompetitionDto(competition, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()
						));
		
	}
	
	// Find competition by id
	@RequestMapping(value="/competition/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findById(@PathVariable("id") Long id) {
		logger.info("competetion-controller findById() invoked: " + id);
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.findById(id), new CycleAvoidMappingContext()
						));

	}
	
	// Find competition by id
	@RequestMapping(value="/competition", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findByCurrent() {
		logger.info("competetion-controller findByCurrent() invoked: ");
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.findByCurrent(), new CycleAvoidMappingContext()
						));

	}

	// Save/update competition
	@RequestMapping(value="/competition/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<CompetitionDto> save(@RequestBody CompetitionDto competitionDto) {
		logger.info("competetion-controller save() invoked. ");
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.saveCompetition(
								competitionMapper.competitionDtoToCompetition(competitionDto, new CycleAvoidMappingContext())
								) 
						, new CycleAvoidMappingContext()));
	}

	// Find players-candidates for competition
	@RequestMapping(value="/competition/{id}/candidates", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PlayerDto>> findCandidatesForCompetition(@PathVariable Long id) {
		logger.info("player-controller findCandidatesForCompetition() invoked: " + id);
		
		return ResponseEntity.ok(
				playerService.findCandidatesForCompetition()
				.stream()
				.map(player -> playerMapper.playerToPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()
						));


	}

	// Players from competition
	@RequestMapping(value="/competition/{id}/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionPlayerDto>> findPlayersFromCompetition(@PathVariable Long id) {
		logger.info("player-controller findPlayersFromCompetition() invoked.");
		
		return ResponseEntity.ok(
				competitionService.findPlayersFromCompetition(id)
				.stream()
				.map(player -> competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
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
	
/* 
 *  Edit functions (admin only) 
 */
	
	// Add player
	@RequestMapping("/competition/{id}/add/{playerId}")
	public ResponseEntity<CompetitionDto> addPlayerToCompetition(@PathVariable("id") Long competitionId, @PathVariable("playerId") Long playerId) {
		logger.info("competetion-controller findPlayers() invoked: " + competitionId + " : " + playerId);
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.addPlayerToCompetition(competitionId, playerId), new CycleAvoidMappingContext()
				));


	}


	// Remove player
	@RequestMapping("/competition/{id}/remove/{playerId}")
	public ResponseEntity<CompetitionDto> removePlayerFromCompetition(@PathVariable("id") Long competitionId, @PathVariable("playerId") Long playerId) {
		logger.info("competetion-controller findPlayers() invoked: " + competitionId + " : " + playerId);
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.removePlayerFromCompetition(competitionId, playerId), new CycleAvoidMappingContext()
				));

	}

	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping("/competition/{id}/generate")
	public ResponseEntity<Competition> generateCompetition(@PathVariable("id") Long competitionId) {
		logger.info("competetion-controller generateCompetition() invoked: " + competitionId);
		
		return ResponseEntity.ok(competitionService.generateCompetition(competitionId));

	}
	
	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping("/competition/{id}/generate2")
	public ResponseEntity<Competition> generateCompetition2(@PathVariable("id") Long competitionId) {
		logger.info("competetion-controller generateCompetition2() invoked: " + competitionId);
		
		return ResponseEntity.ok(competitionService.generateCompetition2(competitionId));

	}
	
	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping("/competition/{id}/generateres")
	public ResponseEntity<CompetitionDto> generateResults(@PathVariable("id") Long id) {
		logger.log(Level.INFO, "competetion-controller generateCompetition2() invoked: {0}", id);
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.generateResults(id), new CycleAvoidMappingContext()
				));

	}
	
	// Find competition by id
	@RequestMapping(value="/competition/{id}/generatecheck", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> generateCheck(@PathVariable("id") Long id) {
		logger.log(Level.INFO, "competetion-controller findById() invoked: {0}", id);
		
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.generateCheck(id), new CycleAvoidMappingContext()
						));

	}
	

}
