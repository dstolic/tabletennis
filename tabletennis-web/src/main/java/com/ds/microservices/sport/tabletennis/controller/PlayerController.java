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

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.service.PlayerService;
import com.ds.microservices.sport.tabletennis.web.SearchCriteria;


@RequestMapping(value = "/players")
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

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String allPlayers(Model model) {
		logger.info("web-service allPlayers() ");

		List<PlayerDto> players = playerService.findAllPlayers();

		logger.info("web-service allPlayers() found: " + players);

		if (players != null)
			model.addAttribute("players", players);

		logger.info("web-service allPlayers() END ");
		return "players";
	}
	

}
