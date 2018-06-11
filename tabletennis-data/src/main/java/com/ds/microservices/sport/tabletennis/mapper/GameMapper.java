package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.model.Game;


@Mapper(componentModel = "spring")
public interface GameMapper {
	
	public abstract Game gameDtoToGame(GameDto gameDTO, @Context CycleAvoidMappingContext context);
    
    public abstract GameDto gameToGameDto(Game game, @Context CycleAvoidMappingContext context);

}
