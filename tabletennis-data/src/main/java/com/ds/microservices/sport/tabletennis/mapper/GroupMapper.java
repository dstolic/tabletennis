package com.ds.microservices.sport.tabletennis.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.dto.GroupDto;
import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.entity.Group;


@Mapper(componentModel = "spring")
public abstract class GroupMapper {
	
	@Autowired
	GameMapper gameMapper;
	

	public abstract Group groupDtoToGroup(GroupDto groupDTO, @Context CycleAvoidMappingContext context);
	
	@Mapping(target="games", expression="java(createGamesList(group))")
	public abstract GroupDto groupToGroupDto(Group group, @Context CycleAvoidMappingContext context);

	public abstract List<GroupDto> groupToGroupDto(List<Group> group, @Context CycleAvoidMappingContext context);
    
	public List<GameDto> createGamesList(Group group) {
		List<Game> games = group.getCompetition().getGames();
		List<GameDto> gamesDto = new ArrayList<GameDto>();
		
		for (Game game : games) {
			if (game.getGroupId() == group.getId() ) {
				GameDto gameDto = gameMapper.gameToGameDto(game, new CycleAvoidMappingContext());
				gamesDto.add(gameDto);
			}
		}
	
	return gamesDto;
	
	}


}
