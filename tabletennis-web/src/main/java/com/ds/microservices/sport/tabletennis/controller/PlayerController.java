package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.service.PlayerService;
import com.ds.microservices.sport.tabletennis.web.SearchCriteria;


@Controller
public class PlayerController {
	
	@Autowired
	protected PlayerService playerService;
	
	protected Logger logger = Logger.getLogger(PlayerController.class.getName());
	
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(value = "/players")
	public String findPlayers(Model model ) {
		logger.info("web-service allPlayers() ");

//		List<PlayerDto> players = playerService.findPlayers(competitionDto);
		List<PlayerDto> players = playerService.findPlayers();

		logger.info("web-service allPlayers() found: " + players);

		if (players != null)
			model.addAttribute("players", players);

		logger.info("web-service allPlayers() END ");
		return "players";
	}
	

	@RequestMapping(value = "/players/activate")
	public String activatePlayers(Model model ) {
		logger.info("web-service activatePlayers() ");

//		List<PlayerDto> players = playerService.findPlayers(competitionDto);
		List<PlayerDto> players = playerService.findPlayers();

		logger.info("web-service activatePlayers() found: " + players);

		if (players != null)
			model.addAttribute("players", players);

		logger.info("web-service activatePlayers() END ");
		return "players";
	}
	
}
