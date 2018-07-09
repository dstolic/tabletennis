package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;
import java.util.Optional;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;

public interface BaseCompetitionService {

	// Find competition by id
	Competition findById(Long id);

	// Find players from competition
	List<CompetitionPlayer> findPlayersFromCompetition();

	// Find current competition
	Competition findByCurrent();


}