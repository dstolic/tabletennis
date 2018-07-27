package com.ds.microservices.sport.tabletennis.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayerPK;


public interface CompetitionPlayerRepository extends PagingAndSortingRepository<CompetitionPlayer, CompetitionPlayerPK> {

	@Query("SELECT count(*) from CompetitionPlayer")
	public long count();

	public List<CompetitionPlayer> findByIdCompetitionId(Long id);
	
	public CompetitionPlayer findByIdCompetitionIdAndIdPlayerId(Long id, Long playerId);

	public List<CompetitionPlayer> findAll(Example<CompetitionPlayer> competitionPlayer);


}
