package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.ds.microservices.sport.tabletennis.report.TestDataDB;
import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;

//@Service
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionServiceTest {

	protected Logger logger = Logger.getLogger(CompetitionServiceTest.class.getName());
	
	@Mock
	protected CompetitionRepository competitionRepository;
	
	public CompetitionServiceTest() {
	}
	
	@Test
	public void testCompetition() {
		logger.info("Test: findById");
		
		Optional<Competition> competition = Optional.of(TestDataDB.createCompetition());
		Mockito.when(competitionRepository.findById(Mockito.anyLong())).thenReturn(competition);
		
		Assert.assertNotNull(competition);
		Assert.assertTrue("Competition name doesn't contain 'Test'", competition.get().getName().contains("Test"));
	
		return ;
	}

	public Competition findById() {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(Mockito.anyLong()).get();
	}

	public Competition findByCurrent() {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findByCurrent(true).get();
	}

	public List<Competition> allCompetitions() {
		// TODO Auto-generated method stub
		return null;
	}
}
