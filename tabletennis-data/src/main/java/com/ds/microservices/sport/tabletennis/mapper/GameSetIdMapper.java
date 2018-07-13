package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ds.microservices.sport.tabletennis.dto.GameSetIdDto;
import com.ds.microservices.sport.tabletennis.entity.GameSetId;


@Mapper(componentModel = "spring")
public interface GameSetIdMapper {
	
	public abstract GameSetId gameSetIdDtoToGameSetId(GameSetIdDto gameSetIdDto, @Context CycleAvoidMappingContext context);
    
	@Mappings({
		@Mapping(expression="java(gameSetIdDto.getGame().getId())", target = "gameId"),
		
	})
    public abstract GameSetIdDto gameSetIdToGameSetIdDto(GameSetId gameSetIdDto, @Context CycleAvoidMappingContext context);
    
}
