package com.ds.microservices.sport.tabletennis;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.ds.microservices.sport.tabletennis.config.DataConfiguration;;


@EnableAutoConfiguration
@Import(DataConfiguration.class)
public class AdminServer {
	
	protected Logger logger = Logger.getLogger(AdminServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name" , "admin-server");
		SpringApplication.run(AdminServer.class, args);
	}

}
