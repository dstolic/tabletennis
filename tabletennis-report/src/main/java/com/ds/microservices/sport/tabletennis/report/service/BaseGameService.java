package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Game;

public interface BaseGameService {

	// Find games from competition
	List<Game> findGamesFromCompetition();

}