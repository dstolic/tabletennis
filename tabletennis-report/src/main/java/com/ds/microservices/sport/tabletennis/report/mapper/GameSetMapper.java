package com.ds.microservices.sport.tabletennis.report.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ds.microservices.sport.tabletennis.report.dto.GameSetDto;
import com.ds.microservices.sport.tabletennis.report.entity.GameSet;

@Mapper(componentModel = "spring", uses = {GameSetIdMapper.class})
public abstract class GameSetMapper {
	
	@Autowired
	PlayerMapper playerMapper;
	
	public abstract GameSet gameSetDtoToGameSet(GameSetDto gameSetDTO, @Context CycleAvoidMappingContext context);
    
    public abstract GameSetDto gameSetToGameSetDto(GameSet gameSet, @Context CycleAvoidMappingContext context);
	
}
