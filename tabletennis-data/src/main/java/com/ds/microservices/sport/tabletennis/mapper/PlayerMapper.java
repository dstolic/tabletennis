package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.model.Player;


@Mapper(componentModel = "spring")
public interface PlayerMapper {
	
	public abstract Player playerDtoToPlayer(PlayerDto playerDTO);
    
    public abstract PlayerDto playerToPlayerDto(Player player);

}
