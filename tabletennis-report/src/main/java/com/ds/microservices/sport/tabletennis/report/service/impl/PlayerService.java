package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.entity.Player;
import com.ds.microservices.sport.tabletennis.report.exceptions.PlayerNotFoundException;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.report.service.BasePlayerService;

@Service
public class PlayerService implements BasePlayerService {

	protected Logger logger = Logger.getLogger(PlayerService.class.getName());

	@Autowired
	protected PlayerRepository playerRepository;

	@Autowired
	protected CompetitionRepository competitionRepository;

	// List of all players
	@Override
	public List<Player> allPlayers() {
		return (List<Player>) playerRepository.findAll();
	}
	
	// Find player by id
	@Override
	public Player findOne(Long id) {
		return playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
	}

}
