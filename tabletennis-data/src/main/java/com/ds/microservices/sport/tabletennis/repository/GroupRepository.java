package com.ds.microservices.sport.tabletennis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.entity.Group;


public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

	@Query("SELECT count(*) from Group")
	public long count();

	public List<Group> findByCompetitionId(Long competitionId);

	public Optional<Group> findById(Long id);

	public Group findByCompetitionIdAndName(Long id, String name);
	

}
