package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.entity.Player;


@Mapper(componentModel = "spring")
public interface PlayerMapper {
	
	public abstract Player playerDtoToPlayer(PlayerDto playerDTO, @Context CycleAvoidMappingContext context);
    
    public abstract PlayerDto playerToPlayerDto(Player player, @Context CycleAvoidMappingContext context);

}
