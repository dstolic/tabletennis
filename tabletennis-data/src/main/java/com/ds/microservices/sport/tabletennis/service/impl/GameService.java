package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.repository.GameRepository;
import com.ds.microservices.sport.tabletennis.service.BaseGameService;

@Service
public class GameService implements BaseGameService {

	protected Logger logger = Logger.getLogger(GameService.class.getName());
	
	@Autowired
	protected GameRepository gameRepository;


	// Find games from competition
	@Override
	public List<Game> findGamesFromCompetition(Long competitionId) {
		logger.info("game-service findGamesFromCompetition() invoked: ");

		List<Game> games = (List<Game>) gameRepository.findByCompetitionId(competitionId);

		logger.info("game-service findGamesFromCompetition() found: " + games);

		return games;
	}

}
