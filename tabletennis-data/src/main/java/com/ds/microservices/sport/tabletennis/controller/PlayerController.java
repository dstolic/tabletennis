package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;
import com.ds.microservices.sport.tabletennis.service.PlayerService;

@RestController
public class PlayerController {

	protected Logger logger = Logger.getLogger(PlayerController.class.getName());
	
	protected PlayerRepository playerRepository;

	protected PlayerService playerService;

	protected CompetitionService competitionService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}


	@RequestMapping("/players")
	public List<PlayerDto> findPlayers() {
		logger.info("player-service findPlayers() invoked: ");
		
		return playerService.findPlayers();


	}

	@RequestMapping("/competition/{id}/players")
	public List<PlayerDto> findPlayersForCompetition(@PathVariable Long id) {
		logger.info("player-service findPlayersForCompetition() invoked: " + id);
		
		if (id == null) {
			return playerService.findPlayers();
		} else {
			return playerService.findPlayersByCompetition(id);
		}


	}

}
