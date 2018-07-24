package com.ds.microservices.sport.tabletennis.report.service;

import java.util.List;

import com.ds.microservices.sport.tabletennis.report.entity.Player;

public interface BasePlayerService {

	List<Player> allPlayers();

	Player findOne(Long id);

}