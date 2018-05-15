package com.ds.microservices.sport.tabletennis.mapper;

import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.model.Competition;

//@Mapper(componentModel = "spring", uses=LeaguePlayerMapper.class)
@Mapper(componentModel = "spring"	)
public interface CompetititonMapper {
	
//	@Mapping(source="leaguePlayersDTO", target="leaguePlayers")
	public abstract Competition competitionDtoToCompetition(CompetitionDto competititonDTO);
    
//	@InheritInverseConfiguration
//	@Mapping(source="leaguePlayers", target="leaguePlayersDTO")
    public abstract CompetitionDto competitionToCompetitionDto(Competition competititon);

//	@Mappings({
//		@Mapping(source="playerDTO", target="player")
//    })
//	public abstract LeaguePlayer leaguePlayerDtoToLeaguePlayer(LeaguePlayerDTO leaguePlayerDTO);
//
//	@Mappings({
//		@Mapping(source="player", target="playerDTO")
//	})
//	public abstract LeaguePlayerDTO leaguePlayerToLeaguePlayerDto(LeaguePlayer leaguePlayer);

//    LeaguePlayerDTO leaguePlayerToLeaguePlayerDto(LeaguePlayer leaguePlayer);
//
//    LeaguePlayer leaguePlayerDtoToLeaguePlayer(LeaguePlayerDTO leaguePlayerDTO);

    //    public List<PlayerDTO> populatePlayers(League league) {
//    	
//        return league.getPlayers().stream()
//    			.map(player -> PlayerMapper.INSTANCE.playerToPlayerDto(player))
//    			.collect(Collectors.toList());
//    }


}
