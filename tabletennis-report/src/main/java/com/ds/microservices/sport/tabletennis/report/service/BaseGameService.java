package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Game;

public interface BaseGameService {

	List<Game> findGamesFromCompetition();

	List<Game> findFinishedGames();

	List<Game> findScheduledGames();

	Game getGame(Long id);

}