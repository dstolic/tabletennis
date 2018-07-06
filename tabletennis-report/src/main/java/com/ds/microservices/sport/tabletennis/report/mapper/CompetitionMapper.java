package com.ds.microservices.sport.tabletennis.report.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.ds.microservices.sport.tabletennis.report.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.report.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.report.entity.Competition;
import com.ds.microservices.sport.tabletennis.report.entity.CompetitionPlayer;

@Mapper(componentModel = "spring", uses=PlayerMapper.class)
public abstract class CompetitionMapper {
	
	@Autowired
	PlayerMapper playerMapper;
	
	public abstract Competition competitionDtoToCompetition(CompetitionDto competititonDTO, @Context CycleAvoidMappingContext context);
    
	@Mapping(target="players", expression="java(createPlayerSet(competititon.getCompetitionPlayers()))")
    public abstract CompetitionDto competitionToCompetitionDto(Competition competititon, @Context CycleAvoidMappingContext context);
	
	public Set<PlayerDto> createPlayerSet(List<CompetitionPlayer> competitionPlayers) {
		
		Set<PlayerDto> players = new HashSet<PlayerDto>();
		
		for (CompetitionPlayer competitionPlayer : competitionPlayers) {
			PlayerDto playerDto = playerMapper.playerToPlayerDto(competitionPlayer.getId().getPlayer(), new CycleAvoidMappingContext());
			if (!players.contains(competitionPlayer.getId().getPlayer())) {
				players.add(playerDto);
			}

//			PlayerDto playerDto = playerMapper.playerToPlayerDto(competitionPlayer.getId().getPlayer(), new CycleAvoidMappingContext());
//			if (!players.contains(playerDto)) {
//				players.add(playerDto);
//			}

		}
	
	return players;
	
	}
}