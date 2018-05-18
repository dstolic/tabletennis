package com.ds.microservices.sport.tabletennis.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.mapper.CompetititonMapper;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.model.Competition;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;

@Service
public class CompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());
	
	protected CompetitionRepository competitionRepository;

	@Autowired
	private CompetititonMapper competitionMapper;

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;

		logger.info("DES CompetitionRepository says system has " + competitionRepository.count() + " competitions.");
	}

	public List<CompetitionDto> all() {
		logger.info("competition-service allCompetitions() invoked: " + competitionRepository.getClass().getName());
		
		List<Competition> competitions = (List<Competition>)competitionRepository.findAll();
		logger.info("competition-service all() found: " + competitions);

		return competitions.stream()
				.map(competition -> competitionMapper.competitionToCompetitionDto(competition))
				.collect(Collectors.toList());
		
				
	}

	public CompetitionDto findOne(Long id) {
		
//		League league = leaguesRepository.findOne(leagueid);
//		logger.info("leagues-service findOne(): league " + league);
//		if(league.getLeaguePlayers() != null) {
//			for (LeaguePlayer leaguePlayer: league.getLeaguePlayers()) {
//				logger.info("leagues-service findOne(): leaguePlayer " + leaguePlayer);
//			}
//		}
//
//		LeagueDTO leagueDTO = leagueMapper.leagueToLeagueDto(leaguesRepository.findOne(leagueid));
//		logger.info("leagues-service findOne(): leagueDTO " + leagueDTO);
//		
//		if(leagueDTO.getLeaguePlayersDTO() != null) {
//			for (LeaguePlayerDTO leaguePlayerDTO : leagueDTO.getLeaguePlayersDTO()) {
//				logger.info("leagues-service findOne(): leaguePlayerDTO 1 " + leaguePlayerDTO);
//			}
//			
//			for (LeaguePlayer leaguePlayer : league.getLeaguePlayers()) {
//				PlayerDTO playerDTO = playerMapper.playerToPlayerDto(leaguePlayer.getPlayer());
//				LeaguePlayerDTO leaguePlayerDTO = new LeaguePlayerDTO();
//				leaguePlayerDTO.setLeagueDTO(leagueDTO);
//				leaguePlayerDTO.setPlayerDTO(playerDTO);
//				
//				logger.info("leagues-service findOne(): leaguePlayerDTO 2 " + leaguePlayerDTO);
//			}
//			
////			leagueDTO.setLeaguePlayers(league.getLeaguePlayers().stream()
////				.map(leaguePlayer -> leaguePlayerMapper.leaguePlayerToLeaguePlayerDto(leaguePlayer))
////				.collect(Collectors.toList()));
//			
//			for (LeaguePlayerDTO leaguePlayerDTO : leagueDTO.getLeaguePlayersDTO()) {
//				logger.info("leagues-service findOne(): leaguePlayerDTO 3 " + leaguePlayerDTO);
//			}
//		}

//		LeaguePlayerDTO leaguePlayerDTO = leaguePlayerMapper.leaguePlayerToLeaguePlayerDto(league.getLeaguePlayers()); 

		
		return competitionMapper.competitionToCompetitionDto(competitionRepository.findOne(id));
//		return leagueDTO;
	}
	
	
	public CompetitionDto add(CompetitionDto competitionDTO) {

		Competition competition = competitionMapper.competitionDtoToCompetition(competitionDTO);
		logger.info("leagues-service new league: " + competition);

		logger.info("DES competitionRepository count: " + competitionRepository.count() + " competitions");
		
		return competitionMapper.competitionToCompetitionDto(competitionRepository.save(competition));
	}


	public void delete(Long competitionId) {
		logger.info("leagues-service delete() invoked: " + competitionId);

		competitionRepository.delete(competitionId);
		
		logger.info("leagues-service delete() done " + competitionId);

	}


}
