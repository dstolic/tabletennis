package com.ds.microservices.sport.tabletennis.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;


@Service
public class PlayerService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	protected Logger logger = Logger.getLogger(PlayerService.class.getName());

	public PlayerService(String playerService) {
		this.serviceUrl = playerService.startsWith("http") ? playerService : "http://" + playerService;
	}

	public List<PlayerDto> findPlayers() {
		logger.info("web-service findPlayers() " + serviceUrl + "/players ");

		String uri = serviceUrl + "/players";
		
		PlayerDto[] players = null;
		try {
			players = restTemplate.getForObject(uri, PlayerDto[].class);
		} catch (HttpClientErrorException e) { // 404
			logger.info("Kao oblanda puce URI (" + uri + ")");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("Kao oblanda puce sve ostalo");
			e.printStackTrace();
		}

		logger.info("web-service findPlayers() END ");
		if (players == null || players.length == 0)
			return null;
		else
			return Arrays.asList(players);
	}

	
	public List<PlayerDto> findPlayersForCompetition(CompetitionDto competitionDto) {
		logger.info("web-service findPlayers() " + serviceUrl + "/players " + competitionDto);

		String uri = serviceUrl;
		if (competitionDto != null) {
			uri += "/competition/"+competitionDto.getId();
		}
		uri += "/players";
		
//		UriTemplate uriTemplate = new UriTemplate(uri);
		
		PlayerDto[] players = null;
		try {
			players = restTemplate.getForObject(uri, PlayerDto[].class);
		} catch (HttpClientErrorException e) { // 404
			logger.info("Kao oblanda puce URI (" + uri + ")");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("Kao oblanda puce sve ostalo");
			e.printStackTrace();
		}

		logger.info("web-service findPlayers() END ");
		if (players == null || players.length == 0)
			return null;
		else
			return Arrays.asList(players);
	}


}
