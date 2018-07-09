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
public class AdminController {

	protected Logger logger = Logger.getLogger(AdminController.class.getName());
	
	private String SERVER = GatewayConfiguration.ADMIN_SERVER;
	
	// Competition
	@RequestMapping(value="/admin/competitions", method = RequestMethod.GET)
	public void competitions(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competitions");
        httpServletResponse.sendRedirect(SERVER + "/admin/competitions");
	}

	// Competition
	@RequestMapping(value="/admin/competition", method = RequestMethod.GET)
	public void currentCompetition(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/1");
	}

	// Competition players
	@RequestMapping(value="/admin/competition/players", method = RequestMethod.GET)
	public void competitionPlayers(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition//players");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/players");
	}

	// Competition games
	@RequestMapping(value="/admin/competition/games", method = RequestMethod.GET)
	public void competitionGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition//games");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/games");
	}

	// Competition finished games
	@RequestMapping(value="/admin/competition/games/finished", method = RequestMethod.GET)
	public void competitionFinishedGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition//games/finished");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/games/finished");
	}

	// Competition finished games
	@RequestMapping(value="/admin/competition/games/scheduled", method = RequestMethod.GET)
	public void competitionScheduledGames(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/games/scheduled");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/games/scheduled");
	}

	// Competition groups
	@RequestMapping(value="/admin/competition/group", method = RequestMethod.GET)
	public void competitionGroups(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/group");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/group");
	}

	// Competition groups by name
	@RequestMapping(value="/admin/competition/group/{name}", method = RequestMethod.GET)
	public void competitionGroupByName(HttpServletResponse httpServletResponse, @PathVariable String name) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/group/{name}");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/group/" + name);
	}


	
	
	// Generate competition 
	@RequestMapping(value="/admin/competition/{id}/generate", method = RequestMethod.GET)
	public void generateCompetition(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1/generate");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/1/generate");

	}

	// Competition
	@RequestMapping(value="/admin/competition/{id}/generate2", method = RequestMethod.GET)
	public void generateCompetition2(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1/generate2");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/1/generate2");

	}

}
