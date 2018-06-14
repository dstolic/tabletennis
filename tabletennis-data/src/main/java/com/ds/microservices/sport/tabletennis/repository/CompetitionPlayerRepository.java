package com.ds.microservices.sport.tabletennis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayerPK;


public interface CompetitionPlayerRepository extends PagingAndSortingRepository<CompetitionPlayer, CompetitionPlayerPK> {

	@Query("SELECT count(*) from CompetitionPlayer")
	public long count();

//	@Query("Select pl from Player pl where active = 1 and id in (select player_id from competition_player where competition_id=1) order by points desc 8")
//	public List<Player> findSeedPlayers(Long competitionId);

//	public Iterable<League> findAll();
	
//	public List<League> findByNameContainingIgnoreCase(String partialName);
	
//	public League save(League leagueDTO) ;
	

}
