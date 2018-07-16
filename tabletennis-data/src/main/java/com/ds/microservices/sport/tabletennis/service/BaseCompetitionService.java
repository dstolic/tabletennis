package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;

public interface BaseCompetitionService {

	List<Competition> allCompetitions();

	Competition findById(Long id);

	Competition saveCompetition(Competition competition);

	void deleteCompetition(Long competitionId);

	Competition addPlayerToCompetition(Long competitionId, Long playerId);

	Competition removePlayerFromCompetition(Long competitionId, Long playerId);

	Competition generateCompetition(Long competitionId);

	Competition generateCompetition2(Long competitionId);

	List<CompetitionPlayer> findPlayersFromCompetition();
	List<CompetitionPlayer> findPlayersFromCompetition(Long competitionId);

	Competition findByCurrent();

	Competition generateResults(Long competitionId);

	Competition generateCheck(Long competitionId);

	Competition activateCheck(Long competitionId);


}