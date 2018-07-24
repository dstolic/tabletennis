package com.ds.microservices.sport.tabletennis.report;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.ds.microservices.sport.tabletennis.report.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.service.BaseCompetitionService;

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
		
		Assert.assertTrue("FORMAT: CUP", competitionConfiguration.getFormat().contains("CUP"));
		Assert.assertTrue("SEEDS: 8", competitionConfiguration.getNumberOfSeeds() == 8);
		Assert.assertTrue("PLAYERS: 32", competitionConfiguration.getNumberOfPlayers() == 32);
	}
	
}
