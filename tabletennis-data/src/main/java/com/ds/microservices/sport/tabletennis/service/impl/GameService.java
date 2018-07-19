package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.entity.GameSet;
import com.ds.microservices.sport.tabletennis.entity.GameSetId;
import com.ds.microservices.sport.tabletennis.exceptions.GameNotFoundException;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.repository.GameRepository;
import com.ds.microservices.sport.tabletennis.repository.GameSetRepository;
import com.ds.microservices.sport.tabletennis.service.BaseGameService;

@Service
public class GameService implements BaseGameService {

	protected Logger logger = Logger.getLogger(GameService.class.getName());
	
	@Autowired
	protected GameRepository gameRepository;

	@Autowired
	protected GameSetRepository gameSetRepository;

	@Autowired
	protected CompetitionRepository competitionRepository;

	@Autowired
	protected GameMapper gameMapper;

	@Override
	public List<Game> findGamesFromCompetition() {
		Competition competition =  competitionRepository.findByCurrent(true);
		return gameRepository.findByCompetitionId(competition.getId());
	}

	@Override
	public List<Game> findGamesFromCompetition(Long competitionId) {
		return gameRepository.findByCompetitionId(competitionId);
	}

	@Override
	public List<Game> findFinishedGames() {
		Competition competition =  competitionRepository.findByCurrent(true);
		return gameRepository.findByCompetitionIdAndFinished(competition.getId(), true);
	}

	@Override
	public List<Game> findFinishedGames(Long competitionId) {
		return gameRepository.findByCompetitionIdAndFinished(competitionId, true);
	}

	@Override
	public List<Game> findScheduledGames() {
		Competition competition =  competitionRepository.findByCurrent(true);
		return gameRepository.findByCompetitionIdAndFinished(competition.getId(), false);
	}

	@Override
	public List<Game> findScheduledGames(Long competitionId) {
		return gameRepository.findByCompetitionIdAndFinished(competitionId, false);
	}

	@Override
	public Game getGame(Long id) {
		Optional<Game> optionalGame = gameRepository.findById(id);
			
		return optionalGame.orElseThrow(GameNotFoundException::new);
	}
	
	@Override
	public Game addGameResult(Long id, GameDto gameDto) {

//		Optional<Game> optionalGame = gameRepository.findById(id);
//		Game game;
//		if (optionalGame.isPresent()) game = optionalGame.orElseThrow(GameNotFoundException::new);
		Game game = gameMapper.gameDtoToGame(gameDto, new CycleAvoidMappingContext());
		
		return gameRepository.save(game);
	}
	
}
