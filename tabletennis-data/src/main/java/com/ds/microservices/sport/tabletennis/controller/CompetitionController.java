package com.ds.microservices.sport.tabletennis.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.PlayerDto;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.service.CompetitionService;
import com.ds.microservices.sport.tabletennis.service.PlayerService;;


@RestController
public class CompetitionController {

	protected Logger logger = Logger.getLogger(CompetitionController.class.getName());
	
//	protected CompetitionRepository leaguesRepository;

	@Autowired
	protected CompetitionService competitionService;
	
	@Autowired
	protected PlayerService playerService;
	
	@Autowired
	public CompetitionController(CompetitionService competitionService) {
		this.competitionService = competitionService;

//		logger.info("DES LeaguesRepository says system has " + leaguesRepository.count() + " leagues");
	}

	@RequestMapping("/competition")
	public ResponseEntity<List<CompetitionDto>> all() {
		logger.info("competition-service all() invoked: ");

		return ResponseEntity.ok(competitionService.all());
	}
	
	@RequestMapping("/competition/{id}/players/ccc")
	public List<PlayerDto> findPlayers(@RequestBody CompetitionDto competitionDto) {
		logger.info("player-service findPlayers() invoked: " + competitionDto);
		
//		if (competitionDto == null) {
			return playerService.findPlayers();
//		} else {
//			return playerService.findPlayersByCompetition();
//		}
	}

	@RequestMapping("/competition/{id}")
	public ResponseEntity<CompetitionDto> findById(@PathVariable("id") Long id) {
		logger.info("competetion-service findById() invoked: " + id);
		
		return ResponseEntity.ok(competitionService.findOne(id));

	}
	



	@RequestMapping(value = "/leagues/add")
	public ResponseEntity<CompetitionDto> add(@RequestBody CompetitionDto leagueDTO) {
		logger.info("leagues-service add() web league: " + leagueDTO );

		return ResponseEntity.ok(competitionService.add(leagueDTO));
	}


	@RequestMapping("/leagues/delete/{leagueid}")
	public void delete(@PathVariable("leagueid") Long leagueid) {
		logger.info("leagues-service delete() invoked: " + leagueid);

		competitionService.delete(leagueid);
		
		logger.info("leagues-service delete() done " + leagueid);

	}


//	@RequestMapping("/leagues/{leagueid}")
//	public LeagueDTO findById(@PathVariable("leagueid") String leagueid) {
//		logger.info("leagues-service findById() invoked: " + leagueid);
//		League league = leaguesRepository.findOne(Long.parseLong(leagueid));
//		LeagueDTO leagueDTO = new LeagueDTO();
//		leagueDTO.setId(league.getId());
//		leagueDTO.setName(league.getName());
//		leagueDTO.setSeason(league.getSeason());
//		leagueDTO.setDescription(league.getDescription());
//		
//		List<LeaguePlayerDTO> leaguePlayersList = new ArrayList<LeaguePlayerDTO>(); 
//		if(league.getLeaguePlayers() != null) {
//			logger.info("league.getLeaguePlayers() (" + league.getLeaguePlayers().size() + ")");
//			for (LeaguePlayer leaguePlayer : league.getLeaguePlayers()) {
//				LeaguePlayerDTO leaguePlayerDTO = new LeaguePlayerDTO();
//
//				PlayerDTO playerDTO = new PlayerDTO();
//				playerDTO.setId(leaguePlayer.getPlayer().getId());
//				playerDTO.setFirstName(leaguePlayer.getPlayer().getFirstName());
//				playerDTO.setLastName(leaguePlayer.getPlayer().getLastName());
//				playerDTO.setEmail(leaguePlayer.getPlayer().getEmail());
//				
////				leaguePlayerDTO.setLeague(leagueDTO);
//				leaguePlayerDTO.setPlayerDTO(playerDTO);
//				
//				leaguePlayersList.add(leaguePlayerDTO);
//			}
//		} else {
//			logger.info("league.getLeaguePlayers() je prazan");
//		}
//
//		leagueDTO.setLeaguePlayers(leaguePlayersList);
//
//		return leagueDTO;
//	}
	

//	@RequestMapping(value = "/leagues/addplayer")
//	public LeagueDTO addplayer(@RequestBody LeagueDTO leagueDTO) {
//		logger.info("leagues-service addplayer() invoked: " + leagueDTO.getDescription() );
//
//		League league = leaguesRepository.findOne(leagueDTO.getId());
//
//		LeaguePlayerDTO leaguePlayerDTO = leagueDTO.getLeaguePlayersDTO().get(leagueDTO.getLeaguePlayersDTO().size());
//		logger.info("leagues-service addplayer() found: " + leaguePlayerDTO );
//		
//		if(leagueDTO.getId() != null && leagueDTO.getId() > 0) {
//			league.setId(leagueDTO.getId());
//		}
//		league.setName(leagueDTO.getName());
//		league.setSeason(leagueDTO.getSeason());
//		league.setDescription(leagueDTO.getDescription());
//		
////		league.setLeaguePlayers(leagueDTO.getLeaguePlayersDTO());
//
//		League league_response = leaguesRepository.save(league);
//		leagueDTO.setId(league.getId());
//		
//		logger.info("leagues-service web league: " + leagueDTO);
//		logger.info("leagues-service new league: " + league_response);
//		logger.info("DES LeaguesRepository changed: " + leaguesRepository.count() + " leagues");
//		
//		return leagueDTO;
//	}

	
	

}
