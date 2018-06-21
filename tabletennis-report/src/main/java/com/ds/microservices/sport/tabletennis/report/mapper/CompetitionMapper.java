package com.ds.microservices.sport.tabletennis.report.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.report.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.report.entity.Competition;

@Mapper(componentModel = "spring", uses=PlayerMapper.class	)
public interface CompetitionMapper {
	
	public abstract Competition competitionDtoToCompetition(CompetitionDto competititonDTO, @Context CycleAvoidMappingContext context);
    
    public abstract CompetitionDto competitionToCompetitionDto(Competition competititon, @Context CycleAvoidMappingContext context);

}
