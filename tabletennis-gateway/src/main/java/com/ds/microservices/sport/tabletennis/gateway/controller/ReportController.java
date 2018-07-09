package com.ds.microservices.sport.tabletennis.gateway.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
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
	
	// Competition players
	@RequestMapping(value="/competition/players", method = RequestMethod.GET)
	public void competitionPlayers(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.REPORT_SERVER + "/competition/players");
        httpServletResponse.sendRedirect(SERVER + "/competition/players");
	}

	// Competition games
	@RequestMapping(value="/competition/games", method = RequestMethod.GET)
	public void competitionGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.REPORT_SERVER + "/competition/games");
        httpServletResponse.sendRedirect(SERVER + "/competition/games");

	}
	
	// Competition finished games
	@RequestMapping(value="/competition/games/finished", method = RequestMethod.GET)
	public void competitionFinishedGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/competition//games/finished");
        httpServletResponse.sendRedirect(SERVER + "/competition/games/finished");
	}

	// Competition finished games
	@RequestMapping(value="/competition/games/scheduled", method = RequestMethod.GET)
	public void competitionScheduledGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/competition/games/scheduled");
        httpServletResponse.sendRedirect(SERVER + "/competition/games/scheduled");
	}

	// Competition groups
	@RequestMapping(value="/competition/group", method = RequestMethod.GET)
	public void competitionGroups(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/competition/group");
        httpServletResponse.sendRedirect(SERVER + "/competition/group");
	}

	// Competition groups by name
	@RequestMapping(value="/competition/group/{name}", method = RequestMethod.GET)
	public void competitionGroupByName(HttpServletResponse httpServletResponse, @PathVariable String name) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/competition/group/{name}");
        httpServletResponse.sendRedirect(SERVER + "/competition/group/" + name);
	}

}
