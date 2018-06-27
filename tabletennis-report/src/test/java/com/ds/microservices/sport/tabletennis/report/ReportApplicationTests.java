package com.ds.microservices.sport.tabletennis.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ds.microservices.sport.tabletennis.report.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.service.BaseCompetitionService;

import org.junit.Assert;
import org.junit.Ignore;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ReportApplicationTests {
	
	@Autowired
	private BaseCompetitionService baseCompetitionService;
	
	@Autowired
	private CompetitionConfiguration competitionConfiguration;

//	@Test
	public void testCompetitionById() {
		Competition competition = baseCompetitionService.findById(new Long(1)); 
		
		Assert.assertNotNull("Competition is null.", competition);
		Assert.assertTrue("Competition name doesn't contain 'Test'", competition.getName().contains("Test"));
	}

//	@Test
	public void testCompetitionConfiguration() {
		
		Assert.assertTrue("FORMAT: CUP", competitionConfiguration.getFORMAT().contains("CUP"));
		Assert.assertTrue("SEEDS: 8", competitionConfiguration.getNUMBER_OF_SEEDS() == 8);
		Assert.assertTrue("PLAYERS: 32", competitionConfiguration.getNUMBER_OF_PLAYERS() == 32);
	}
	
}
