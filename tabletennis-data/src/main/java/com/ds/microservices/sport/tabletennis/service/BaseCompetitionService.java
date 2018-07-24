package com.ds.microservices.sport.tabletennis.service;

import java.util.List;
import java.util.Optional;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.entity.Player;

public interface BaseCompetitionService {

	List<Competition> allCompetitions();

	Competition findById(Long id);

	List<CompetitionPlayer> findPlayersFromCompetition();
	List<CompetitionPlayer> findPlayersFromCompetition(Long competitionId);

	Competition findByCurrent();

	Competition saveCompetition(Competition competition);

	void deleteCompetition(Long competitionId);

	List<Player> addPlayerToCompetition(Long playerId);
	Competition addPlayerToCompetition(Long competitionId, Long playerId);

	Competition removePlayerFromCompetition(Long competitionId, Long playerId);

	Competition generateCompetition();
	Competition generateCompetition(Long competitionId);

	Competition generateCompetition2(Long competitionId);

	Competition generateResults(Long competitionId);

	Competition generateCheck(Long competitionId);

	Competition activateCheck(Long competitionId);


}