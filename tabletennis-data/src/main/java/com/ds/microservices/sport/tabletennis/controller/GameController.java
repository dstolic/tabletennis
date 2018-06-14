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
import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.service.BaseGameService;
import com.ds.microservices.sport.tabletennis.service.BasePlayerService;;


@RequestMapping("/admin")
@RestController
public class GameController {

	protected Logger logger = Logger.getLogger(GameController.class.getName());
	
	@Autowired
	protected BaseCompetitionService competitionService;
	
	@Autowired
	protected BasePlayerService playerService;
	
	@Autowired
	protected BaseGameService gameService;
	
	@Autowired
	public GameController(BaseCompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@Autowired
	protected CompetitionConfiguration competitionConfiguration;
	

	

}
