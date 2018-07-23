package com.ds.microservices.sport.tabletennis.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.entity.Player;

public interface BasePlayerService {

	List<Player> allPlayers();

	Player findOne(Long id);

	Player savePlayer(Player player);

	Player activatePlayer(Long id, boolean active);

	List<Player> findCandidatesForCompetition();

}