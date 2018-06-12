package com.ds.microservices.sport.tabletennis.report.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.report.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.report.model.Player;


@Mapper(componentModel = "spring")
public interface PlayerMapper {
	
	public abstract Player playerDtoToPlayer(PlayerDto playerDTO, @Context CycleAvoidMappingContext context);
    
    public abstract PlayerDto playerToPlayerDto(Player player, @Context CycleAvoidMappingContext context);

}
