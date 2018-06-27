package com.ds.microservices.sport.tabletennis.report.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayerPK;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.report.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.report.service.BaseCompetitionService;

@Service
public class CompetitionService implements BaseCompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());
	
	private final CompetitionRepository competitionRepository;
	private final CompetitionPlayerRepository competitionPlayerRepository;
	

	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository, CompetitionPlayerRepository competitionPlayerRepository) {
		this.competitionRepository = competitionRepository;
		this.competitionPlayerRepository = competitionPlayerRepository;
	}

	// Find competition by id
	@Override
	public Competition findById(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(id).get();
	}

	// Find current competition
	@Override
	public Competition findByCurrent() {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findByCurrent(true).get();
	}

	// Find players from competition
	@Override
	public List<CompetitionPlayer> findPlayersFromCompetition() {
		logger.info("competition-service findPlayersFromCompetition() invoked: ");

		Competition competition =  competitionRepository.findByCurrent(true).get();
		CompetitionPlayerPK pk = new CompetitionPlayerPK(competition, null);
		
		List<CompetitionPlayer> players = competitionPlayerRepository.findByIdCompetitionId(competition.getId());

		logger.info("competition-service findPlayersFromCompetition() found: " + players);

		return players;
	}

	@Override
	public List<Competition> allCompetitions() {
		// TODO Auto-generated method stub
		return null;
	}

}
