package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ds.microservices.sport.tabletennis.dto.CompetitionPlayerPKDto;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayerPK;


@Mapper(componentModel = "spring")
public interface CompetitionPlayerPKMapper {
	
	public abstract CompetitionPlayerPK competitionPlayerPKDtoToCompetitionPlayerPK(CompetitionPlayerPKDto competitionPlayerDto, @Context CycleAvoidMappingContext context);
    
	@Mappings({
		@Mapping(expression="java(competitionPlayerPKDto.getCompetition().getId())", target = "competitionId"),
//		@Mapping(expression="java(competitionPlayerPKDto.getPlayer().getId())", target = "playerId")
		
	})
    public abstract CompetitionPlayerPKDto competitionPlayerPKToCompetitionPlayerPKDto(CompetitionPlayerPK competitionPlayerPKDto, @Context CycleAvoidMappingContext context);
    
}
