package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.Game;
import com.ds.microservices.sport.tabletennis.report.service.BaseGameService;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.GameRepository;

@Service
public class GameService implements BaseGameService {

	protected Logger logger = Logger.getLogger(GameService.class.getName());
	
	private final CompetitionRepository competitionRepository;
	private final GameRepository gameRepository;


	@Autowired
	public GameService(GameRepository gameRepository, CompetitionRepository competitionRepository) {
		this.gameRepository = gameRepository;
		this.competitionRepository = competitionRepository;
	}


	// Find games from competition
	@Override
	public List<Game> findGamesFromCompetition() {
		logger.info("game-service findGamesFromCompetition() invoked: ");

		Competition competition =  competitionRepository.findByCurrent(true).get();
		
		List<Game> games = gameRepository.findByCompetitionId(competition.getId());

		logger.info("game-service findGamesFromCompetition() found: " + games);

		return games;
	}

}
