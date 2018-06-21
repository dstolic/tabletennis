package com.ds.microservices.sport.tabletennis.gateway.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ds.microservices.sport.tabletennis.gateway.config.GatewayConfiguration;


@Configuration
//@PropertySource("classpath:gateway-server.yml")
public class GatewayConfiguration {

	protected Logger logger = Logger.getLogger(GatewayConfiguration.class.getName());

	public static String ADMIN_SERVER = "http://localhost:2222" ;
	
	public static String REPORT_SERVER = "http://localhost:3333";
	
}
