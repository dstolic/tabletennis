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
		
		logger.info("EXAMPLE " + playerRepository.findAll());
		
		return (List<Player>) playerRepository.findAll();
	}
	
	// Find player by id
	@Override
	public Player findOne(Long id) {
		return playerRepository.findById(id).get();
	}

	// Find player with 'query by Example' (for future use)
//	@Override
//	public List<Player> allPlayers() {
//		logger.info("player-service all() invoked: ");
//		
//		Player player = new Player();
//		player.setFirstName("Goran");
//		player.setActive(true);
//
//		ExampleMatcher matcher = ExampleMatcher.matching()     
//				.withMatcher("firstname", match -> match.contains());                          
//
////		Example<Player> example = Example.of(player, matcher);
//		Example<Player> example = Example.of(player);
//		
//		logger.info("EXAMPLE " + playerRepository.findAll(example));
//		
//		return (List<Player>) playerRepository.findAll(example);
//	}
	
}
