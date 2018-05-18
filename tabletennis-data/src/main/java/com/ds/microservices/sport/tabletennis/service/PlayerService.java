package com.ds.microservices.sport.tabletennis.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.model.Player;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;

@Service
public class PlayerService {

	protected Logger logger = Logger.getLogger(PlayerService.class.getName());
	
	protected PlayerRepository playerRepository;

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;

		logger.info("DES PlayerRepository says system has " + playerRepository.count() + " players");
	}


	public List<PlayerDto> findPlayers() {
		logger.info("player-service findPlayers() invoked: ");

		List<Player> result = (List<Player>)playerRepository.findAll();
		
		logger.info("player-service findPlayers() found: " + result);

		return result.stream()
			.map(player -> playerMapper.playerToPlayerDto(player))
			.collect(Collectors.toList());
	}

	public List<PlayerDto> findPlayersByCompetition(Long competitionId) {
		logger.info("player-service findPlayersByCompetition() invoked: ");

		List<Player> result = (List<Player>)playerRepository.findAll();
		
		logger.info("player-service findPlayersByCompetition() found: " + result);

		return result.stream()
			.map(player -> playerMapper.playerToPlayerDto(player))
			.collect(Collectors.toList());
	}

}