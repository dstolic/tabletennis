package com.ds.microservices.sport.tabletennis.start;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistrationServer {

	protected Logger logger = Logger.getLogger(ServiceRegistrationServer.class.getName());

	public static void main(String[] args) {
		
		System.setProperty("spring.config.name", "registration-server");
		SpringApplication.run(ServiceRegistrationServer.class, args);
		
	}

}
