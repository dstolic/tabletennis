package com.ds.microservices.sport.tabletennis.gateway.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GatewayController {

	protected Logger logger = Logger.getLogger(GatewayController.class.getName());
	
	// Competition
	@RequestMapping(value="/admin/competition", method = RequestMethod.GET)
	public void competitionAdmin(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("competition start");
        httpServletResponse.sendRedirect("http://localhost:2222/admin/competition/1");

	}

	// Competition
	@RequestMapping(value="/competition", method = RequestMethod.GET)
	public void competition(HttpServletResponse httpServletResponse) throws IOException {
		logger.info("competition start");
        httpServletResponse.sendRedirect("http://localhost:3333/competition");

	}
	
}
