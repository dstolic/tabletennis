package com.ds.microservices.sport.tabletennis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.model.Competition;


public interface CompetitionRepository extends PagingAndSortingRepository<Competition, Long> {

	@Query("SELECT count(*) from Competition")
	public long count();

//	public Iterable<League> findAll();
	
//	public List<League> findByNameContainingIgnoreCase(String partialName);
	
//	public League save(League leagueDTO) ;
	

}
