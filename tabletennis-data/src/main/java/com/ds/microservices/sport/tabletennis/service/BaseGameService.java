package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Game;

public interface BaseGameService {

	List<Game> findGamesFromCompetition();
	List<Game> findGamesFromCompetition(Long competitionId);

	Game findGameFromCompetition(Long gameId);
	Game findGameFromCompetition(Long competitionId, Long gameId);

	List<Game> findFinishedGames();
	List<Game> findFinishedGames(Long competitionId);

	List<Game> findScheduledGames();
	List<Game> findScheduledGames(Long competitionId);

	Game getGame(Long id);
	Game addGameResult(Long id, Game game);


}