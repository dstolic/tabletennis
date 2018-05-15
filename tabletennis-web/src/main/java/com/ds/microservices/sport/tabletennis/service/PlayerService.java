package com.ds.microservices.sport.tabletennis.service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;


@Service
public class PlayerService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	protected Logger logger = Logger.getLogger(PlayerService.class.getName());

	public PlayerService(String playersService) {
		this.serviceUrl = playersService.startsWith("http") ? playersService : "http://" + playersService;
	}

	public List<PlayerDto> findAllPlayers() {
		logger.info("web-service findAllPlayers() " + serviceUrl + "/players/all");

		PlayerDto[] players = null;
		try {
			players = restTemplate.getForObject(serviceUrl + "/players/all", PlayerDto[].class);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		logger.info("web-service findAllPlayers() END ");
		if (players == null || players.length == 0)
			return null;
		else
			return Arrays.asList(players);
	}


}
