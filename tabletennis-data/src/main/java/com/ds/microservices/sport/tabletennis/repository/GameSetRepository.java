package com.ds.microservices.sport.tabletennis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.entity.GameSet;


public interface GameSetRepository extends PagingAndSortingRepository<GameSet, Long> {

	@Query("SELECT count(*) from GameSet")
	public long count();

	
}
