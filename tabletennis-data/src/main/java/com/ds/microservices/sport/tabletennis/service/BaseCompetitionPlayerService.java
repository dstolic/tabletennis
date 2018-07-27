package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;

public interface BaseCompetitionPlayerService {

	List<CompetitionPlayer> findPlayersFromCompetition();
	List<CompetitionPlayer> findPlayersFromCompetition(Long competitionId);

	CompetitionPlayer findPlayerFromCompetition(Long id);

	CompetitionPlayer findPlayerFromCompetition(Long id, Long playerid);

}