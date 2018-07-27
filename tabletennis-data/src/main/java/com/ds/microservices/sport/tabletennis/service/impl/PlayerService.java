package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.entity.Player;
import com.ds.microservices.sport.tabletennis.exceptions.PlayerNotFoundException;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.BasePlayerService;

@Service
public class PlayerService implements BasePlayerService {

	protected Logger logger = Logger.getLogger(PlayerService.class.getName());

	@Autowired
	protected PlayerRepository playerRepository;

	// List of all players
	@Override
	public List<Player> allPlayers() {
		return (List<Player>) playerRepository.findAll();
	}
	
	// Find player by id
	@Override
	public Player findOne(Long id) {
		return playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
	}

	// Save/update player
	@Override
	public Player savePlayer(Player player) {
		return playerRepository.save(player);
	}

	// Activate/deactivate player (to check: could we use save() method for both activation also) 
	@Override
	public Player activatePlayer(Long id, boolean active) {
		Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
		player.setActive(active);
		
		return playerRepository.save(player);
	}

	// Find players-candidates for competition
	@Override
	public List<Player> findCandidatesForCompetition() {
		return playerRepository.findByActive(true);
	}

}
