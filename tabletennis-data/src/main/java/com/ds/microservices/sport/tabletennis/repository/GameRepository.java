package com.ds.microservices.sport.tabletennis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.model.Game;


public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

	@Query("SELECT count(*) from Game")
	public long count();

	
	public List<Game> findByFinished(boolean finished);
	
	public List<Game> findByCompetitionId(Long competitionId);

//	@Query("Select pl from Player pl where active = 1 and id in (select player_id from competition_player where competition_id=1) order by points desc 8")
//	public List<Player> findSeedPlayers(Long competitionId);

//	public Iterable<League> findAll();
	
//	public List<League> findByNameContainingIgnoreCase(String partialName);
	
//	public League save(League leagueDTO) ;
	

}
