package com.ds.microservices.sport.tabletennis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.model.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

	@Query("SELECT count(*) from Player")
	public long count();


//	public Iterable<Player> findAll();
	
//	public Player findByEmail(String email);

//	public List<Player> findByEmailContainingIgnoreCase(String partialName);


}
