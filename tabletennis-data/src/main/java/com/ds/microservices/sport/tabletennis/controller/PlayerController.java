package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.PlayerService;

@RestController
public class PlayerController {

	protected Logger logger = Logger.getLogger(PlayerController.class.getName());
	
	protected PlayerRepository playerRepository;

	protected PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}


	@RequestMapping("/players/all")
	public List<PlayerDto> findAllPlayers() {
		logger.info("player-service allPlayers() invoked: ");

		return playerService.findAllPlayers();

	}


}
