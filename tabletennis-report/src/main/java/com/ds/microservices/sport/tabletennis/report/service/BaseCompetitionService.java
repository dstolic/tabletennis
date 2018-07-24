package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;

public interface BaseCompetitionService {

	List<Competition> allCompetitions();

	Competition findById(Long id);

	List<CompetitionPlayer> findPlayersFromCompetition();

	Competition findByCurrent();


}