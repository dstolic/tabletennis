package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.entity.Game;


@Mapper(componentModel = "spring", uses= { GameSetMapper.class})
public interface GameMapper {
	
	public abstract Game gameDtoToGame(GameDto gameDTO, @Context CycleAvoidMappingContext context);
    
    public abstract GameDto gameToGameDto(Game game, @Context CycleAvoidMappingContext context);

}
