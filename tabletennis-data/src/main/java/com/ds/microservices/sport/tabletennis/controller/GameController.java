package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.model.Competition;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;
import com.ds.microservices.sport.tabletennis.service.GameService;
import com.ds.microservices.sport.tabletennis.service.PlayerService;;


@RequestMapping("/admin")
@RestController
public class GameController {

	protected Logger logger = Logger.getLogger(GameController.class.getName());
	
	@Autowired
	protected CompetitionService competitionService;
	
	@Autowired
	protected PlayerService playerService;
	
	@Autowired
	protected GameService gameService;
	
	@Autowired
	public GameController(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@Autowired
	protected CompetitionConfiguration competitionConfiguration;
	

	

}
