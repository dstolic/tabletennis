package com.ds.microservices.sport.tabletennis.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.mapper.CompetititonMapper;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.model.Competition;
import com.ds.microservices.sport.tabletennis.model.Game;
import com.ds.microservices.sport.tabletennis.model.Player;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;

@Service
public class PlayerService {

	protected Logger logger = Logger.getLogger(PlayerService.class.getName());

	protected PlayerRepository playerRepository;

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	private CompetititonMapper competititonMapper;

	@Autowired
	private CompetitionService competitionService; 

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;

		logger.info("DES PlayerRepository says system has " + playerRepository.count() + " players");
	}

	// List of all players
	public List<PlayerDto> all() {
		logger.info("player-service all() invoked: ");

		List<Player> players = (List<Player>) playerRepository.findAll();

		logger.info("player-service all() found: " + players);

		return players.stream()
				.map(player -> playerMapper.playerToPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList());
	}
	
	// List of all players without mapping
	public List<Player> allClean() {
		logger.info("player-service allClean() invoked: ");

		List<Player> players = (List<Player>) playerRepository.findAll();

		logger.info("player-service allClean() found: " + players);

		return players;
	}
	
	// Find player by id
	public PlayerDto findOneDto(Long id) {
		return playerMapper.playerToPlayerDto(playerRepository.findOne(id), new CycleAvoidMappingContext());
	}

	// Find player by id
	public Player findOne(Long id) {
		return playerRepository.findOne(id);
	}

	// Save/update player
	public PlayerDto save(PlayerDto playerDto) {
		logger.info("player-service save() invoked: ");

		return playerMapper.playerToPlayerDto(
				playerRepository.save(
					playerMapper.playerDtoToPlayer(playerDto, new CycleAvoidMappingContext())), new CycleAvoidMappingContext());
	}

	// Activate/deactivate player (to check: could we use save() method for both activation also) 
	public PlayerDto activatePlayer(Long id, boolean active) {
		logger.info("player-service activatePlayer() invoked: ");
		
		Player player = playerRepository.findOne(id);
		player.setActive(active);
		
		return playerMapper.playerToPlayerDto(
				playerRepository.save(player), new CycleAvoidMappingContext());
	}

	// Find players - candidates to enter competition - in progress
	public List<PlayerDto> findPlayersForCompetition(Long competitionId) {
		logger.info("player-service findPlayersForCompetition() invoked: ");

		List<Player> players = (List<Player>) playerRepository.findByActive(true);

		logger.info("player-service findPlayersForCompetition() found: " + players);

		return players.stream()
				.map(player -> playerMapper.playerToPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList());
	}

	// Find players-candidates for competition
	public List<PlayerDto> findCandidatesForCompetition() {
		logger.info("player-service findCandidatesForCompetition() invoked: ");

		List<Player> players = (List<Player>) playerRepository.findByActive(true);

		logger.info("player-service findCandidatesForCompetition() found: " + players);

		return players.stream()
				.map(player -> playerMapper.playerToPlayerDto(player, new CycleAvoidMappingContext()))
				.collect(Collectors.toList());
	}

	// Find players-candidates for competition
	public List<Player> findCandidatesForCompetitionNoTransform() {
		logger.info("player-service findCandidatesForCompetitionNoTransform() invoked: ");

		List<Player> players = (List<Player>) playerRepository.findByActiveOrderByPointsDesc(true);

		logger.info("player-service findCandidatesForCompetitionNoTransform() found: " + players);

		return players;
	}


}
