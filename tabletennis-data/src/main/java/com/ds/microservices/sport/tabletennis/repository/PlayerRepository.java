package com.ds.microservices.sport.tabletennis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

	@Query("SELECT count(*) from Player")
	public long count();

//	@Query("select id, firstName, lastName, points, active from Player p where p.active=1 ")
//	@Query("select id, firstName, lastName, points, active from Player p where p.active=1 ")
	public List<Player> findByActive(boolean active);

	public List<Player> findByActiveOrderByPointsDesc(boolean active);

//	@Query("INSERT into CompetitionPlayer values (:competition, :player)")
//	public void savePlayer(@Param("competition") Long competitionId, @Param("player") Long playerId);


//	public List<Player> findByCompetitionId(Long competitionId);


}
