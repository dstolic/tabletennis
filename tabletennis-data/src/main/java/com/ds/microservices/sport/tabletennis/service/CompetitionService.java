package com.ds.microservices.sport.tabletennis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.dto.CompetitionDto;
import com.ds.microservices.sport.tabletennis.dto.GameDto;
import com.ds.microservices.sport.tabletennis.mapper.CompetititonMapper;
import com.ds.microservices.sport.tabletennis.mapper.CycleAvoidMappingContext;
import com.ds.microservices.sport.tabletennis.mapper.PlayerMapper;
import com.ds.microservices.sport.tabletennis.model.Competition;
import com.ds.microservices.sport.tabletennis.model.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.model.Game;
import com.ds.microservices.sport.tabletennis.model.Group;
import com.ds.microservices.sport.tabletennis.model.Player;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.util.CompetitionUtil;

@Service
public class CompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());
	
	protected CompetitionRepository competitionRepository;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private CompetititonMapper competitionMapper;

	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;

		logger.info("DES CompetitionRepository says system has " + competitionRepository.count() + " competitions.");
	}

	// List of all players
	public List<CompetitionDto> all() {
		logger.info("competition-service all() invoked.");
		
		List<Competition> competitions = (List<Competition>)competitionRepository.findAll();
		
		logger.info("competition-service all() found: " + competitions);

		return competitions.stream()
				.map(competition -> competitionMapper.competitionToCompetitionDto(competition, new CycleAvoidMappingContext()))
				.collect(Collectors.toList());
	}

	// Find competition by id
	public CompetitionDto findById(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionMapper.competitionToCompetitionDto(competitionRepository.findOne(id), new CycleAvoidMappingContext());
	}

	public Competition findByIdNoTransform(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findOne(id);
	}

	// Find competition by id
	public CompetitionDto findPlayersForCompetition(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionMapper.competitionToCompetitionDto(competitionRepository.findOne(id), new CycleAvoidMappingContext());
	}

	// Save/update competition
	public CompetitionDto save(CompetitionDto competitionDTO) {
		logger.info("competition-service save invoked. ");

		return competitionMapper.competitionToCompetitionDto(
				competitionRepository.save(
						competitionMapper.competitionDtoToCompetition(competitionDTO, new CycleAvoidMappingContext())), new CycleAvoidMappingContext());
	}


	// ???
	public void delete(Long competitionId) {
		logger.info("leagues-service delete() invoked: " + competitionId);

		competitionRepository.delete(competitionId);
		
		logger.info("leagues-service delete() done " + competitionId);

	}

	// Add player to competition
	public CompetitionDto addPlayerToCompetition(Long competitionId, Long playerId) {
		logger.info("player-service addPlayerToCompetition() invoked. ");
		
		Competition competition = competitionRepository.findOne(competitionId);
		Player player = playerService.findOne(playerId);
		if (competition.getPlayers() != null) {
//			competition.getPlayers().add(new CompetitionPlayer(player, false));
			competition.getPlayers().add(player);

			competition = competitionRepository.save(competition);
		}
		
		
		logger.info("player-service addPlayerToCompetition() End. " + competition);
		return competitionMapper.competitionToCompetitionDto(competition, new CycleAvoidMappingContext());


	}
	
	// Remove player from competition
	public CompetitionDto removePlayerFromCompetition(Long competitionId, Long playerId) {
		logger.info("player-service removePlayerFromCompetition() invoked. ");
		
		Competition competition = competitionRepository.findOne(competitionId);
		Player player = playerService.findOne(playerId);
		if (competition.getPlayers() != null) {
			competition.getPlayers().remove(player);

			competition = competitionRepository.save(competition);
		}
		
		
		logger.info("player-service removePlayerFromCompetition() End. " + competition);
		return competitionMapper.competitionToCompetitionDto(competition, new CycleAvoidMappingContext());


	}
	
	// Generate competition
	public Competition generateCompetition(Long competitionId) {
		// TODO
		// 1. Read configuration for competition or use default
		// 2. Fill players list - up to NUMBER_OF_PLAYERS (optional)
		// 3. Set seed players - up to NUMBER_OF_SEEDS (optional) - additional attribute to Player, int seed
		// Before generating groups/games, check if the numbers are correct:
		// 	a) number of players should be power of 2
		//  b) if a) is false, add more players or add one more qualification round for players with less points.
		// Number of groups is the same as number of seed players.
		// Groups generation will break players list into (players_count \ number_of_seeds) 'groups for drawing'.
		// Those groups will be filled according to points.
		// For example: 32 players, 8 seeds. 4 groups for drawing; 1-8 (seeds), 9-16, 17-24. 25-32
		// Groups for competition will be formed using 1 random player from all 4 drawing groups.
		// If there is qualification round, we'll draw qualification match as a member of group.
		
		// TODO
		// Remember players after competition generation, but before competition starts.
		// Their status at the start determine competition (number of points will made them seed players and so on)
		// Player's initial status will be used during competition.
		
		Competition competition = findByIdNoTransform(competitionId);
		
		CompetitionUtil utils = new CompetitionUtil();
		boolean autogeneratedPlayers = true;
		boolean autogeneratedSeeds = true;

//		Competition competition = competitionRepository.findOne(competitionId);
//		Set<Player> playersCandidates = competition.getPlayers();
		List<Player> playersCandidates = playerService.findCandidatesForCompetitionNoTransform();
		
//		Seed players determination is put on hold, no solution at the moment; possible hibernate problem
// 		List is mocked for time being.
//		List<Player> seedPlayers = competitionRepository.findSeedPlayers(competitionId);
//		logger.info("seedPlayers " + seedPlayers);
//		List<Player> sortedPlayers = new ArrayList<Player>(new TreeSet<Player>(playersCandidates));
		
		// All players are temporary here, just for testing
//		List<Player> allPlayers = playerService.allClean();
		// Number of players and seed players are fine, continue
		if(utils.setupCompleted(competition, autogeneratedPlayers, autogeneratedSeeds)) {
			// determine groups
			List<Group> groups = utils.createGroups(competition, playersCandidates, autogeneratedPlayers, autogeneratedSeeds);
			competition.setGroups(groups);

			List<Game> games = utils.createGames(competition);
			competition.setGames(games);
		}
		
		for (CompetitionPlayer competitionPlayer : competition.getCompetitionPlayers()) {
			logger.info(competitionPlayer.toString());
		}
		competition = competitionRepository.save(competition);
		
		return competition;
	}

}
