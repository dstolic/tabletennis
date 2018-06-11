package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.model.Competition;

@Mapper(componentModel = "spring", uses=PlayerMapper.class	)
public interface CompetititonMapper {
	
	public abstract Competition competitionDtoToCompetition(CompetitionDto competititonDTO, @Context CycleAvoidMappingContext context);
    
    public abstract CompetitionDto competitionToCompetitionDto(Competition competititon, @Context CycleAvoidMappingContext context);

}
