package com.ds.microservices.sport.tabletennis.gateway.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.gateway.config.GatewayConfiguration;


@RestController
public class GatewayController {

	protected Logger logger = Logger.getLogger(GatewayController.class.getName());
	
	// Competition
//	@RequestMapping(value="/admin/competition", method = RequestMethod.GET)
	public void competitionAdmin(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1");
        httpServletResponse.sendRedirect(GatewayConfiguration.ADMIN_SERVER + "/admin/competition/1");

	}

	// Competition
//	@RequestMapping(value="/competition", method = RequestMethod.GET)
	public void competition(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("redirect to " + GatewayConfiguration.REPORT_SERVER + "/competition");
        httpServletResponse.sendRedirect(GatewayConfiguration.REPORT_SERVER + "/competition");

	}
	
}
