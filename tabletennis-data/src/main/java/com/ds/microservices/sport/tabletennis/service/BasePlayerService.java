package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.entity.Player;

public interface BasePlayerService {

	// List of all players
	List<Player> allPlayers();

	// Find player by id
	Player findOne(Long id);

	// Save/update player
	Player savePlayer(Player player);

	// Activate/deactivate player (to check: could we use save() method for both activation also) 
	Player activatePlayer(Long id, boolean active);

	// Find players-candidates for competition
	List<Player> findCandidatesForCompetition();

}