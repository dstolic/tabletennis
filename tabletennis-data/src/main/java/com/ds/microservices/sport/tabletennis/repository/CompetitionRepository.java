package com.ds.microservices.sport.tabletennis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.entity.Competition;


public interface CompetitionRepository extends PagingAndSortingRepository<Competition, Long> {

	@Query("SELECT count(*) from Competition")
	public long count();

	public Optional<Competition> findById(Long id);

	public Competition findByCurrent(boolean current);
	

}
