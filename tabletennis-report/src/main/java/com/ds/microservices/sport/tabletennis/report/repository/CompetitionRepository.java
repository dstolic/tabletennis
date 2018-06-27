package com.ds.microservices.sport.tabletennis.report.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;



public interface CompetitionRepository extends PagingAndSortingRepository<Competition, Long> {

	@Query("SELECT count(*) from Competition")
	public long count();

	public Optional<Competition> findById(Long id);

	public Optional<Competition> findByCurrent(boolean current);
	
//	@Query("Select pl from Player pl where active = 1 and id in (select player_id from competition_player where competition_id=1) order by points desc 8")
//	public List<Player> findSeedPlayers(Long competitionId);

//	public Iterable<League> findAll();
	
//	public List<League> findByNameContainingIgnoreCase(String partialName);
	
//	public League save(League leagueDTO) ;
	

}
