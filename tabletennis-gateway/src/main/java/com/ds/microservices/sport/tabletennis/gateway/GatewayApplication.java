package com.ds.microservices.sport.tabletennis.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name" , "gateway-server");
		SpringApplication.run(GatewayApplication.class, args);
	}
}
