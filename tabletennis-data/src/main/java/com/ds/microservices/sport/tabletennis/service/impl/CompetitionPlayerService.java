package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionPlayerService;

@Service
public class CompetitionPlayerService implements BaseCompetitionPlayerService {

	protected Logger logger = Logger.getLogger(CompetitionPlayerService.class.getName());

	@Autowired
	protected PlayerRepository playerRepository;
	
	@Autowired
	protected CompetitionRepository competitionRepository;

	@Autowired
	protected CompetitionPlayerRepository competitionPlayerRepository;

	// Find players from competition
	// TODO: Try to find optimized version. 'findByIdCompetitionId' produces one SELECT statement per competitionPlayer
	@Override
	public List<CompetitionPlayer> findPlayersFromCompetition(Long competitionId) {
		return competitionPlayerRepository.findByIdCompetitionId(competitionId);
	}

	@Override
	public List<CompetitionPlayer> findPlayersFromCompetition() {
		Competition competition =  competitionRepository.findByCurrent(true);

		return competitionPlayerRepository.findByIdCompetitionId(competition.getId());
	}

	@Override
	public CompetitionPlayer findPlayerFromCompetition(Long playerId) {
		Competition competition =  competitionRepository.findByCurrent(true);
//		Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
//		CompetitionPlayerPK competitionPlayerId = new CompetitionPlayerPK(competition, player); 

		return competitionPlayerRepository.findByIdCompetitionIdAndIdPlayerId(competition.getId(), playerId);
//		return competitionPlayerRepository.findById(competitionPlayerId).orElseThrow(CompetitionPlayerNotFoundException::new);
	}

	@Override
	public CompetitionPlayer findPlayerFromCompetition(Long id, Long playerId) {
//		Competition competition =  competitionRepository.findById(id).orElseThrow(CompetitionNotFoundException::new);
//		Player player = playerRepository.findById(playerId).orElseThrow(PlayerNotFoundException::new);
//		CompetitionPlayerPK competitionPlayerId = new CompetitionPlayerPK(competition, player); 

		return competitionPlayerRepository.findByIdCompetitionIdAndIdPlayerId(id, playerId);
	}


}
