package com.ds.microservices.sport.tabletennis.report.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.ds.microservices.sport.tabletennis.report.dto.GameDto;
import com.ds.microservices.sport.tabletennis.report.dto.GameStatus;
import com.ds.microservices.sport.tabletennis.report.dto.GroupDto;
import com.ds.microservices.sport.tabletennis.report.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.report.entity.Game;
import com.ds.microservices.sport.tabletennis.report.entity.Group;
import com.ds.microservices.sport.tabletennis.report.util.PlayerDtoComparator;

@Mapper(componentModel = "spring", uses = CompetitionPlayerMapper.class)
public abstract class GroupMapper {
	protected Logger logger = Logger.getLogger(GroupMapper.class.getName());
	
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
	
	@AfterMapping
	public void calculate(@MappingTarget List<GroupDto> groups) {
		
		for (GroupDto groupDto : groups) {
			for (GameDto gameDto : groupDto.getGames()) {
				int winIncrement = 0;
				int loseIncrement = 0;
				if(gameDto.getFinishedStatus() == GameStatus.FINISHED) {
					winIncrement = 2;
					loseIncrement = 1;
				} else if(gameDto.getFinishedStatus() == GameStatus.FORFEITED) {
					winIncrement = 2;
					loseIncrement = 1;
				} else if(gameDto.getFinishedStatus() == GameStatus.NOT_PLAYED) {
					winIncrement = 2;
				}
				for (PlayerDto playerDto : groupDto.getPlayers()) {
					if (gameDto.getFinishedStatus() != GameStatus.VOID) {
						if (playerDto.getId() ==  gameDto.getPlayerHome().getId()) {
							playerDto.addGamesPointsFor(gameDto.getPointsHome());
							playerDto.addGamesPointsAgainst(gameDto.getPointsAway());
							
							if(gameDto.getPointsHome() > gameDto.getPointsAway()) {
								playerDto.addGroupPoints(winIncrement);
							} else {
								playerDto.addGroupPoints(loseIncrement);
							}
	
						} else if (playerDto.getId() ==  gameDto.getPlayerAway().getId()) {
							playerDto.addGamesPointsFor(gameDto.getPointsAway());
							playerDto.addGamesPointsAgainst(gameDto.getPointsHome());
	
							if(gameDto.getPointsAway() > gameDto.getPointsHome()) {
								playerDto.addGroupPoints(winIncrement);
							} else {
								playerDto.addGroupPoints(loseIncrement);
							}
						}
					}
				}
			}
			// Sort groups
			groupDto.getPlayers().sort(new PlayerDtoComparator());
		}
	}

}
