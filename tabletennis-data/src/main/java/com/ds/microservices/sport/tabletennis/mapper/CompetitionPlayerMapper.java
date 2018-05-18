package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.CompetitionPlayerDto;
import com.ds.microservices.sport.tabletennis.model.Competition;
import com.ds.microservices.sport.tabletennis.model.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.model.Player;


//@Mapper(componentModel = "spring", uses=PlayerMapper.class)
@Mapper(componentModel = "spring")
public interface CompetitionPlayerMapper {
	
//	@Mappings({
//		@Mapping(source="playerDTO", target="player")
//    })
	public abstract CompetitionPlayer competitionPlayerDtoToCompetitionPlayer(CompetitionPlayerDto competitionPlayerDTO);

//	@Mappings({
//		@Mapping(source="player", target="playerDTO")
//	})
	public abstract CompetitionPlayerDto competitionPlayerToCompetitionPlayerDto(CompetitionPlayer competitionPlayer);
		

//	public abstract Player playerDtoToPlayer(PlayerDTO playerDTO);
    
//    public abstract PlayerDTO playerToPlayerDto(Player player);

}
