package com.ds.microservices.sport.tabletennis.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;


@Service
public class CompetitionService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());

	public CompetitionService(String playerService) {
		this.serviceUrl = playerService.startsWith("http") ? playerService : "http://" + playerService;
	}

	// List
	public List<CompetitionDto> all() {
		logger.info("web-service all() " + serviceUrl + "/competition");
		
		
		CompetitionDto[] competitions = null;
		String uri = serviceUrl + "/competition";
//		http://PLAYER-SERVICE/leagues/list
		try {
			competitions = restTemplate.getForObject(uri, CompetitionDto[].class);
		} catch (HttpClientErrorException u) { // 404
			logger.info("Kao oblanda puce URI (" + uri + ")");
			u.printStackTrace();
		} catch (Exception e) {
			logger.info("Kao oblanda puce sve ostalo");
			e.printStackTrace();
		}

		
		return competitions != null ? Arrays.asList(competitions) : new ArrayList<CompetitionDto>();
	}


	public CompetitionDto findById(Long id) {
		String uri = serviceUrl + "/competition/"+id;
		logger.info("findById() invoked: for " + uri);
		return restTemplate.getForObject(uri, CompetitionDto.class, id);
	}


	// Save
	public CompetitionDto save(CompetitionDto leagueDTO)  {
		logger.info("web-service save(LeagueDTO leagueDTO) ");
		String uri = serviceUrl + "/leagues/add";
//		Map<String, String> vars = new HashMap<String, String>();
//		vars.put("id", "0");
		
//		HttpEntity<LeagueDTO> request = new HttpEntity<>(leagueDTO);
		CompetitionDto leagueDTO_response = new CompetitionDto(); 

		try {
			 RequestEntity<CompetitionDto> request = RequestEntity
				     .post(new URI(uri))
				     .accept(MediaType.APPLICATION_JSON)
				     .body(leagueDTO);
			
			logger.info("web-service uri " + uri);
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//			restTemplate.put(uri, request);
//			restTemplate.
			ResponseEntity<CompetitionDto> response = restTemplate.exchange(request, CompetitionDto.class);
			leagueDTO_response = response.getBody();
//			assertTrue(response.getStatusCode(), equals(HttpStatus.OK));
			
			logger.info("web-service response " + response);
			logger.info("web-service response leagueDTO " + leagueDTO);
			logger.info("web-service response leagueDTO_response " + leagueDTO_response);

		} catch (HttpClientErrorException e) { // 404
			// Nothing found
			logger.info("Kao oblanda");
			e.printStackTrace();
			throw e;
		} catch (URISyntaxException e) { 
			logger.info("Kao oblanda puce URI");
			e.printStackTrace();
//			throw e;
		} catch (Exception e) {
			logger.info("Kao oblanda puce sve ostalo");
			e.printStackTrace();
			throw e;
		}
		
		return leagueDTO_response;
		
	}

	public void delete(CompetitionDto leagueDTO) {
		String uri = serviceUrl + "/leagues/delete/" + leagueDTO.getId();
		
		logger.info("web-service delete() " + uri);
		logger.info("web-service delete() " + leagueDTO);
		
		restTemplate.delete(uri, CompetitionDto.class, leagueDTO);
		
		logger.info("web-service delete() done");
		return; 

	}


}
