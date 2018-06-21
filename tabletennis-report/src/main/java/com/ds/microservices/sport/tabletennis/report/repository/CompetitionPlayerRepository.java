package com.ds.microservices.sport.tabletennis.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayerPK;


public interface CompetitionPlayerRepository extends PagingAndSortingRepository<CompetitionPlayer, CompetitionPlayerPK> {

	@Query("SELECT count(*) from CompetitionPlayer")
	public long count();

	public List<CompetitionPlayer> findByIdCompetitionId(Long id);


}
