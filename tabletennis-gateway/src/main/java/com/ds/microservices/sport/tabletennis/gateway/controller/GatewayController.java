package com.ds.microservices.sport.tabletennis.gateway.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.gateway.config.GatewayConfiguration;


@RestController
public class GatewayController {

	protected Logger logger = Logger.getLogger(GatewayController.class.getName());
	
	// Competition
//	@RequestMapping(value="/admin/competition", method = RequestMethod.GET)
	public void competitionAdmin(HttpServletResponse httpServletResponse) throws IOException {
		logger.log(Level.INFO, "redirect to {0}/admin/competition/1", GatewayConfiguration.ADMIN_SERVER);
        httpServletResponse.sendRedirect(GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1");

	}

	// Competition
//	@RequestMapping(value="/competition", method = RequestMethod.GET)
	public void competition(HttpServletResponse httpServletResponse) throws IOException {
		logger.log(Level.INFO, "redirect to {0}/competition", GatewayConfiguration.ADMIN_SERVER);
        httpServletResponse.sendRedirect(GatewayConfiguration.REPORT_SERVER + "/competition");

	}
	
}
