package com.ds.microservices.sport.tabletennis.gateway.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.gateway.config.GatewayConfiguration;


@RestController
public class ReportController {

	protected Logger logger = Logger.getLogger(ReportController.class.getName());
	
	private String SERVER = GatewayConfiguration.REPORT_SERVER;

	// Competition
	@RequestMapping(value="/competition", method = RequestMethod.GET)
	public void competition(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.REPORT_SERVER + "/competition");
        httpServletResponse.sendRedirect(SERVER + "/competition");
	}
	
	@RequestMapping(value="/competition/games", method = RequestMethod.GET)
	public void competitionGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.REPORT_SERVER + "/competition/games");
        httpServletResponse.sendRedirect(SERVER + "/competition/games");

	}
	
	@RequestMapping(value="/competition/players", method = RequestMethod.GET)
	public void competitionPlayers(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.REPORT_SERVER + "/competition/players");
        httpServletResponse.sendRedirect(SERVER + "/competition/players");

	}
	
}
