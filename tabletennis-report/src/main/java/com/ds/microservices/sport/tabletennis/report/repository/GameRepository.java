package com.ds.microservices.sport.tabletennis.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.report.entity.Game;


public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

	@Query("SELECT count(*) from Game")
	public long count();

	public List<Game> findByFinished(boolean finished);
	
	public List<Game> findByCompetitionId(Long competitionId);

	public List<Game> findByCompetitionIdAndFinished(Long competitionId, boolean finished);

}
