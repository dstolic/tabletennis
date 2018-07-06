package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.entity.Player;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.report.service.BasePlayerService;

@Service
public class PlayerService implements BasePlayerService {

	protected Logger logger = Logger.getLogger(PlayerService.class.getName());

	@Autowired
	protected PlayerRepository playerRepository;

	@Autowired
	protected CompetitionRepository competitionRepository;

	// List of all players
	@Override
	public List<Player> allPlayers() {
		logger.info("player-service all() invoked: ");
		
		Player player = new Player();
		player.setFirstName("Goran");
		player.setActive(true);

		ExampleMatcher matcher = ExampleMatcher.matching()     
				.withMatcher("firstname", match -> match.contains());                          

//		Example<Player> example = Example.of(player, matcher);
		Example<Player> example = Example.of(player);
		
		logger.info("EXAMPLE " + playerRepository.findAll(example));
		
		return (List<Player>) playerRepository.findAll(example);
	}
	
	// Find player by id
	@Override
	public Player findOne(Long id) {
		return playerRepository.findById(id).get();
	}

	// Save/update player
	@Override
	public Player savePlayer(Player player) {
		logger.info("player-service save() invoked: ");

		return playerRepository.save(player);
	}

	// Activate/deactivate player (to check: could we use save() method for both activation also) 
	@Override
	public Player activatePlayer(Long id, boolean active) {
		logger.info("player-service activatePlayer() invoked: ");
		
		Player player = playerRepository.findById(id).get();
		player.setActive(active);
		
		return playerRepository.save(player);
	}

	// Find players-candidates for competition
	@Override
	public List<Player> findCandidatesForCompetition() {
		logger.info("player-service findCandidatesForCompetition() invoked: ");

		return (List<Player>) playerRepository.findByActive(true);
	}

}
