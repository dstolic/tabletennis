package com.ds.microservices.sport.tabletennis.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.mapper.CompetititonMapper;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.model.Game;
import com.ds.microservices.sport.tabletennis.repository.GameRepository;

@Service
public class GameService {

	protected Logger logger = Logger.getLogger(GameService.class.getName());
	
	protected GameRepository gameRepository;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameMapper gameMapper;

	@Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;

		logger.info("DES gameRepository says system has " + gameRepository.count() + " games in competitions.");
	}

	// Find games from competition
	public List<GameDto> findGamesFromCompetition(Long competitionId) {
		logger.info("player-service findGamesFromCompetition() invoked: ");

//		List<Game> games = (List<Game>) gameRepository.findAll();
		List<Game> games = (List<Game>) gameRepository.findByCompetitionId(competitionId);

		logger.info("game-service findGamesFromCompetition() found: " + games);

		return games.stream()
				.map(game -> gameMapper.gameToGameDto(game, new CycleAvoidMappingContext()))
				.collect(Collectors.toList());
	}

}
