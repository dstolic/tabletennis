package com.ds.microservices.sport.tabletennis.report.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CompetitionTest {
	
//	@Autowired
	private CompetitionServiceTest competitionService;
	
//	@Mock
	private CompetitionRepository competitionRepository;
	
//	@Autowired
//	private CompetitionConfiguration competitionConfiguration;
	
//	@Before
	public void init() {
//		this.competitionService = new CompetitionServiceTest(competitionRepository);
		this.competitionService = new CompetitionServiceTest();
	}

//	@Test
	public void testCompetitionById() {
		Competition competition = competitionService.findById(); 
		
		Assert.assertNotNull(competition);
		Assert.assertTrue(competition.getName().contains("Test"));
	}

	@Ignore
	public void testCompetitionConfiguration() {
		
//		Assert.assertTrue(CompetitionConfiguration.FORMAT.contains("CUP"));
//		Assert.assertTrue(CompetitionConfiguration.NUMBER_OF_SEEDS == 8);
//		Assert.assertTrue(CompetitionConfiguration.NUMBER_OF_PLAYERS == 32);
	}
	
}
