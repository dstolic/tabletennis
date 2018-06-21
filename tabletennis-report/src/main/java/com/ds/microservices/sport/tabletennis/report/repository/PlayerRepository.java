package com.ds.microservices.sport.tabletennis.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.report.entity.Game;
import com.ds.microservices.sport.tabletennis.report.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

	@Query("SELECT count(*) from Player")
	public long count();

	public List<Player> findByActive(boolean active);

	public List<Player> findByActiveOrderByPointsDesc(boolean active);


}
