package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.dto.CompetitionPlayerDto;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;


@Mapper(componentModel = "spring", uses= { CompetitionPlayerPKMapper.class})
public interface CompetitionPlayerMapper {
	
	public abstract CompetitionPlayer competitionPlayerDtoToCompetitionPlayer(CompetitionPlayerDto competitionPlayerDTO, @Context CycleAvoidMappingContext context);
    
    public abstract CompetitionPlayerDto competitionPlayerToCompetitionPlayerDto(CompetitionPlayer competitionPlayer, @Context CycleAvoidMappingContext context);
    
}
