package com.ds.microservices.sport.tabletennis.report.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ds.microservices.sport.tabletennis.report.dto.GameDto;
import com.ds.microservices.sport.tabletennis.report.dto.GameStatus;
import com.ds.microservices.sport.tabletennis.report.entity.Game;


@Mapper(componentModel = "spring", uses= { GameSetMapper.class})
public abstract class GameMapper {
	
	public abstract Game gameDtoToGame(GameDto gameDto, @Context CycleAvoidMappingContext context);
    
	@Mapping(target="finishedStatus", expression="java(findGameStatus(gameDto))")
    public abstract GameDto gameToGameDto(Game game, @Context CycleAvoidMappingContext context);
    
    public GameStatus findGameStatus(GameDto gameDto) {
    	return GameStatus.values()[gameDto.getFinished()];
    }

}
