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
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.report.entity.Game;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.repository.GameRepository;

//@Service
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionPlayerServiceTest {

	protected Logger logger = Logger.getLogger(CompetitionPlayerServiceTest.class.getName());
	
//	@Mock
	@Autowired
	protected CompetitionRepository competitionRepository;
	
//	@Mock
	@Autowired
	protected CompetitionPlayerRepository competitionPlayerRepository;
	
	public CompetitionPlayerServiceTest() {
	}
	
	@Test
	public void testGames() {
		logger.info("Test: competitionPlayer");
		
		Optional<Competition> competition = competitionRepository.findByCurrent(true);
		List<CompetitionPlayer> players = competitionPlayerRepository.findByIdCompetitionId(competition.get().getId());
		
		Assert.assertNotNull(competition);
		Assert.assertNotNull(players);
		Assert.assertTrue("There should be 32 players in competition", players.size() == 32);
	
		return ;
	}

}
