package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;

public interface BaseCompetitionService {

	// List of all competitions
	List<Competition> allCompetitions();

	// Find competition by id
	Competition findById(Long id);

	// Save/update competition
	Competition saveCompetition(Competition competition);

	// Delete competition
	void deleteCompetition(Long competitionId);

	// Add player to competition
	Competition addPlayerToCompetition(Long competitionId, Long playerId);

	// Remove player from competition
	Competition removePlayerFromCompetition(Long competitionId, Long playerId);

	// Generate competition
	Competition generateCompetition(Long competitionId);

	// Generate competition, second part
	Competition generateCompetition2(Long competitionId);

	// Find players from competition
	List<CompetitionPlayer> findPlayersFromCompetition();
	List<CompetitionPlayer> findPlayersFromCompetition(Long competitionId);

	// Find current competition
	Competition findByCurrent();



}