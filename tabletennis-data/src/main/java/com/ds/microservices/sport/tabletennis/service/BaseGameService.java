package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Game;

public interface BaseGameService {

	// Find games from competition
	List<Game> findGamesFromCompetition(Long competitionId);

}