package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Player;

public interface BasePlayerService {

	// List of all players
	List<Player> allPlayers();

	// Find player by id
	Player findOne(Long id);

}