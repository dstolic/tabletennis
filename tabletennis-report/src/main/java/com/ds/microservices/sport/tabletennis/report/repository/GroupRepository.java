package com.ds.microservices.sport.tabletennis.report.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.report.entity.Group;



public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

	public Optional<Group> findById(Long id);

	public List<Group> findByCompetitionId(Long competitionId);
	
//	@Query("Select pl from Player pl where active = 1 and id in (select player_id from competition_player where competition_id=1) order by points desc 8")
//	public List<Player> findSeedPlayers(Long competitionId);

//	public Iterable<League> findAll();
	
//	public List<League> findByNameContainingIgnoreCase(String partialName);
	
//	public League save(League leagueDTO) ;
	

}
