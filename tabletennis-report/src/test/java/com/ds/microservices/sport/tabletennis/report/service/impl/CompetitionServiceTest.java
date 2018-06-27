package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.io.InputStream;
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
import com.ds.microservices.sport.tabletennis.report.mapper.CompetitionMapper;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionServiceTest {

	protected Logger logger = Logger.getLogger(CompetitionServiceTest.class.getName());
	
	@Mock
	private CompetitionRepository competitionRepository;
	
	@Mock
	private CompetitionPlayerRepository competitionPlayerRepository;

	private CompetitionService competitionService;
	
	@Autowired
	private CompetitionMapper competitionMapper;
	
	private Competition competitionJson;

	@Before
	public void init() {
		this.competitionService = new CompetitionService(competitionRepository, competitionPlayerRepository);

		try {
			InputStream inJson = Competition.class.getResourceAsStream("/competition.json");
			competitionJson = new ObjectMapper().readValue(inJson, Competition.class);
						
		} catch (Exception e) {
			logger.info("MOSHA");
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		logger.info("competition-service-test: findById");
		logger.info("competition-service-test: competitionJson " + competitionJson);
		
		Optional<Competition> competitionMock = Optional.of(competitionJson);
		Mockito.when(competitionRepository.findById(Mockito.anyLong())).thenReturn(competitionMock);

		Competition competition = competitionService.findById(1l);
		
		Assert.assertNotNull("Competition doesn't exist.", competition);
		Assert.assertTrue("Competition name doesn't contain 'Test'", competition.getName().contains("Test"));
		Assert.assertFalse("Completed competition", competition.isCompleted());
	
		return ;
	}

	@Test
	public void findByCurrent() {
		logger.info("competition-service-test: findByCurrent");
	
		Optional<Competition> competitionMock = Optional.of(competitionJson);
		Mockito.when(competitionRepository.findByCurrent(Mockito.anyBoolean())).thenReturn(competitionMock);

		Competition competition = competitionService.findByCurrent();
		
		Assert.assertNotNull("Competition doesn't exist.", competition);
		Assert.assertTrue("Competition name doesn't contain 'Test'", competition.getName().contains("Test"));
		Assert.assertTrue("Not current competition", competition.isCurrent());

		return ;
	}

//	@Ignore
//	public List<Competition> allCompetitions() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
