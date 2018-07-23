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
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.CompetitionPlayerDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.mapper.CompetitionMapper;
import com.ds.microservices.sport.tabletennis.mapper.CompetitionPlayerMapper;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.service.BaseGameService;
import com.ds.microservices.sport.tabletennis.service.BasePlayerService;


@RequestMapping("/admin")
@RestController
public class CompetitionController {

	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
// Configuration
	@Autowired
	protected CompetitionConfiguration competitionConfiguration;

// Services
	@Autowired
	protected BaseCompetitionService competitionService;
	
	@Autowired
	protected BasePlayerService playerService;
	
	@Autowired
	protected BaseGameService gameService;
	
// Mappers
	@Autowired
	private CompetitionMapper competitionMapper;

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	protected CompetitionPlayerMapper competitionPlayerMapper;


/* 
 *  Common functions 
 */
		
	// List of all competitions
	@RequestMapping(value="/competitions", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionDto>> allCompetitions() {
		return ResponseEntity.ok(competitionService.allCompetitions()
				.stream()
				.map(competition -> competitionMapper.competitionToCompetitionDto(competition, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()
						));
	}
	
	// Find competition by id
	@RequestMapping(value="/competition/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.findById(id), new CycleAvoidMappingContext()
						));
	}
	
	// Find current competition
	@RequestMapping(value="/competition", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> findByCurrent() {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.findByCurrent(), new CycleAvoidMappingContext()
						));
	}

	// Find players-candidates for competition
	@RequestMapping(value="/competition/{id}/candidates", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PlayerDto>> findCandidatesForCompetition(@PathVariable Long id) {
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
		return ResponseEntity.ok(
				competitionService.findPlayersFromCompetition(id)
				.stream()
				.map(player -> competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}

	// Players from competition
	@RequestMapping(value="/competition/players", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CompetitionPlayerDto>> findPlayersFromCompetition() {
		return ResponseEntity.ok(
				competitionService.findPlayersFromCompetition()
				.stream()
				.map(player -> competitionPlayerMapper.competitionPlayerToCompetitionPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList()));
	}
	
/* 
 *  Edit functions (admin only) 
 */
	
	// Save/update competition
	@RequestMapping(value="/competition/add", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<CompetitionDto> save(@RequestBody CompetitionDto competitionDto) {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.saveCompetition(
								competitionMapper.competitionDtoToCompetition(competitionDto, new CycleAvoidMappingContext())
								) 
						, new CycleAvoidMappingContext()));
	}

	// Add player to competition
	@RequestMapping(value="/competition/{id}/add/{playerId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<CompetitionDto> addPlayerToCompetition(@PathVariable("id") Long competitionId, @PathVariable("playerId") Long playerId) {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.addPlayerToCompetition(competitionId, playerId), new CycleAvoidMappingContext()
				));
	}

	// Add player current to competition
	@RequestMapping(value="/competition/add/{playerId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<List<PlayerDto>> addPlayerToCurrentCompetition(@PathVariable("playerId") Long playerId) {
		return ResponseEntity.ok(
						competitionService.addPlayerToCompetition(playerId)
						.stream()
						.map(player -> playerMapper.playerToPlayerDto(player, new CycleAvoidMappingContext()))
						.collect(Collectors.toList())
				);
	}

	// Remove player
	@RequestMapping(value="/competition/{id}/remove/{playerId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.PUT)
	public ResponseEntity<CompetitionDto> removePlayerFromCompetition(@PathVariable("id") Long competitionId, @PathVariable("playerId") Long playerId) {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.removePlayerFromCompetition(competitionId, playerId), new CycleAvoidMappingContext()
				));
	}

	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping(value="/competition/generate", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<Competition> generateCompetition() {
		return ResponseEntity.ok(competitionService.generateCompetition());
	}
	
	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping(value="/competition/{id}/generate", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<Competition> generateCompetition(@PathVariable("id") Long competitionId) {
		return ResponseEntity.ok(competitionService.generateCompetition(competitionId));
	}
	
	// Return Competition (temporary). Return Dto in final version.
	// Generate competition
	@RequestMapping(value="/competition/{id}/generate2", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<Competition> generateCompetition2(@PathVariable("id") Long competitionId) {
		return ResponseEntity.ok(competitionService.generateCompetition2(competitionId));
	}
	
	// Generate competition results (For testing only. Remove in final version.). 
	@RequestMapping(value="/competition/{id}/generateres", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public ResponseEntity<CompetitionDto> generateResults(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.generateResults(id), new CycleAvoidMappingContext()
				));
	}
	
	// Check if competition data may be generated (games, groups)
	@RequestMapping(value="/competition/{id}/generatecheck", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.GET)
	public ResponseEntity<CompetitionDto> generateCheck(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				competitionMapper.competitionToCompetitionDto(
						competitionService.generateCheck(id), new CycleAvoidMappingContext()
						));
	}

}
