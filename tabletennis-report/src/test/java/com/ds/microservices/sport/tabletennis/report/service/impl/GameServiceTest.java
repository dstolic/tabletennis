package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.Game;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.GameRepository;

//@Service
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

	protected Logger logger = Logger.getLogger(GameServiceTest.class.getName());
	
//	@Mock
	@Autowired
	protected CompetitionRepository competitionRepository;
	
//	@Mock
	@Autowired
	protected GameRepository gameRepository;
	
	public GameServiceTest() {
	}
	
	@Test
	public void testGames() {
		logger.info("Test: games");
		
//		Optional<Competition> competition = Optional.of(TestDataDB.createCompetition());
		Optional<Competition> competition = competitionRepository.findByCurrent(true);
//		Mockito.when(gameRepository.findByCompetitionId(Mockito.anyLong())).thenReturn(new ArrayList<Game>());
//		Mockito.when(gameRepository.findByCompetitionId(Mockito.anyLong())).thenReturn(new ArrayList<Game>());
		List<Game> games = gameRepository.findByCompetitionId(competition.get().getId());
		
		Assert.assertNotNull(competition);
		Assert.assertNotNull(games);
		Assert.assertTrue("There should be 48 games in competition", games.size() == 48);
	
		return ;
	}

}
