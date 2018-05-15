package com.ds.microservices.sport.tabletennis.start;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.ds.microservices.sport.tabletennis.controller.PlayerController;
import com.ds.microservices.sport.tabletennis.controller.CompetitionController;
import com.ds.microservices.sport.tabletennis.service.PlayerService;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters=false)
public class WebServer {
	
	protected Logger logger = Logger.getLogger(WebServer.class.getName());

	public final static String PLAYER_SERVICE_URL = "http://PLAYER-SERVICE";
	
	public static void main(String[] args) {
		
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
		
	}
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public PlayerService playerService() {
		return new PlayerService(PLAYER_SERVICE_URL);
	}

	@Bean
	public PlayerController playerController() {
		return new PlayerController(playerService());
	}

	@Bean
	public CompetitionService competitionService() {
		return new CompetitionService(PLAYER_SERVICE_URL);
	}

	@Bean
	public CompetitionController competitionController() {
		return new CompetitionController(competitionService());
	}
}
