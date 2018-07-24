package com.ds.microservices.sport.tabletennis.gateway.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfiguration {

	protected Logger logger = Logger.getLogger(GatewayConfiguration.class.getName());

	public static String ADMIN_SERVER = "http://localhost:2222" ;
	
	public static String REPORT_SERVER = "http://localhost:3333";
	
}
