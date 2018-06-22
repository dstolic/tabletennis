package com.ds.microservices.sport.tabletennis.gateway.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.gateway.config.GatewayConfiguration;


@RestController
public class AdminController {

	protected Logger logger = Logger.getLogger(AdminController.class.getName());
	
	private String SERVER = GatewayConfiguration.ADMIN_SERVER;
	
	// Competition
	@RequestMapping(value="/admin/competition", method = RequestMethod.GET)
	public void competition(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1");
        httpServletResponse.sendRedirect(SERVER + "/admin/competition/1");

	}

	// Competition
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
