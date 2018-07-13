package com.ds.microservices.sport.tabletennis.report;

import com.ds.microservices.sport.tabletennis.report.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.report.dto.GameDto;
import com.ds.microservices.sport.tabletennis.report.entity.Competition;

public class TestDataDB {
	
	public static CompetitionDto createCompetitionDto() {
		
		CompetitionDto competitionDto = new CompetitionDto();
		competitionDto.setId(new Long(1));
		competitionDto.setName("TestCompetition");
		competitionDto.setCompleted(false);
		competitionDto.setCurrent(true);
		
		return competitionDto;
	}
	
	public static Competition createCompetition() {
		
		Competition competition = new Competition();
		competition.setId(new Long(1));
		competition.setName("TestCompetition");
		competition.setCompleted(false);
		competition.setCurrent(true);
		
		return competition;
	}

	public static GameDto createGameDto() {
		
		GameDto gameDto = new GameDto();
		gameDto.setId(new Long(1));
		gameDto.setCompetition(createCompetitionDto());
		gameDto.setFinished(1);
		
		return gameDto;
	}
	
}
