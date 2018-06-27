package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.Game;
import com.ds.microservices.sport.tabletennis.report.mapper.GameMapper;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

	protected Logger logger = Logger.getLogger(GameServiceTest.class.getName());
	
	@Mock
	private CompetitionRepository competitionRepository;
	
	@Mock
	private GameRepository gameRepository;
	
	private GameService gameService;
	
	@Autowired
	private GameMapper gameMapper;
	
	private Competition competitionJson;

	@Before
	public void init() {
		this.gameService = new GameService(gameRepository, competitionRepository);
		
		try {
			InputStream inJson = Competition.class.getResourceAsStream("/competition.json");
			competitionJson = new ObjectMapper().readValue(inJson, Competition.class);
						
			
		} catch (Exception e) {
			logger.info("MOSHA");
			e.printStackTrace();
		}
	}
	
	@Test
	public void findGamesFromCompetition() {
		logger.info("Test: games");
		
		Optional<Competition> competitionMock = Optional.of(competitionJson);
		Mockito.when(gameRepository.findByCompetitionId(Mockito.anyLong())).thenReturn(competitionMock.get().getGames());
		Mockito.when(competitionRepository.findByCurrent(Mockito.anyBoolean())).thenReturn(competitionMock);
		
		List<Game> games = gameService.findGamesFromCompetition();
		
		Assert.assertNotNull("Games doesn't exist.", games);
		Assert.assertTrue("There should be 48 games in competition", games.size() == 48);
	
		return ;
	}

}
