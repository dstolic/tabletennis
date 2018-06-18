package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.entity.Group;
import com.ds.microservices.sport.tabletennis.entity.Player;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotCompletedException;
import com.ds.microservices.sport.tabletennis.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.repository.GameRepository;
import com.ds.microservices.sport.tabletennis.repository.GroupRepository;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.util.CompetitionUtil;

@Service
public class CompetitionService implements BaseCompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());
	
	@Autowired
//	@PersistenceContext(unitName = "Competition")
	private EntityManager entityManager;
	
	@Autowired
	protected CompetitionRepository competitionRepository;
	
	@Autowired
	protected CompetitionPlayerRepository competitionPlayerRepository;

	@Autowired
	protected PlayerRepository playerRepository;

	@Autowired
	protected GameRepository gameRepository;

	@Autowired
	protected GroupRepository groupRepository;


	// List of all competitions
	@Override
	public List<Competition> allCompetitions() {
		logger.info("competition-service all() invoked.");
		
		return (List<Competition>)competitionRepository.findAll();
	}

	// Find competition by id
	@Override
	public Competition findById(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(id).get();
	}

	@Override
	public Competition findByIdNoTransform(Long id) {
		logger.info("competition-service findById invoked. ");
	
		return competitionRepository.findById(id).get();
	}

	// Find competition by id
//	@Override
//	public Competition findPlayersForCompetition(Long id) {
//		logger.info("competition-service findById invoked. ");
//	
//		return competitionRepository.findOne(id);
//	}

	// Save/update competition
	@Override
	public Competition saveCompetition(Competition competition) {
		logger.info("competition-service save invoked. ");

		return competitionRepository.save(competition);
	}


	// ???
	/* (non-Javadoc)
	 * @see com.ds.microservices.sport.tabletennis.service.AdminCompetitionService#delete(java.lang.Long)
	 */
	@Override
	public void deleteCompetition(Long competitionId) {
		logger.info("leagues-service delete() invoked: " + competitionId);

		competitionRepository.deleteById(competitionId);
		
		logger.info("leagues-service delete() done " + competitionId);

	}

	// Add player to competition
	@Override
	public Competition addPlayerToCompetition(Long competitionId, Long playerId) {
		logger.info("player-service addPlayerToCompetition() invoked. ");
		
		Competition competition = competitionRepository.findById(competitionId).get();
		Player player = playerRepository.findById(playerId).get();
		if (competition.getCompetitionPlayers() != null) {
//			competition.getPlayers().add(new CompetitionPlayer(player, false));
			competition.getCompetitionPlayers().add(new CompetitionPlayer(competition, player));

			competition = competitionRepository.save(competition);
		}
		
		
		logger.info("player-service addPlayerToCompetition() End. " + competition);
		return competition;


	}
	
	// Remove player from competition
	@Override
	public Competition removePlayerFromCompetition(Long competitionId, Long playerId) {
		logger.info("player-service removePlayerFromCompetition() invoked. ");
		
		Competition competition = competitionRepository.findById(competitionId).get();
		Player player = playerRepository.findById(playerId).get();
		if (competition.getCompetitionPlayers() != null) {
			competition.getCompetitionPlayers().remove(new CompetitionPlayer(competition, player));

			competition = competitionRepository.save(competition);
		}
		
		
		logger.info("player-service removePlayerFromCompetition() End. " + competition);
		return competition;


	}
	
	// Generate competition
	@Override
	public Competition generateCompetition(Long competitionId) throws CompetitionNotCompletedException {
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
		utils.init(competition);
		boolean autogeneratedPlayers = true;
		boolean autogeneratedSeeds = true;

//		Competition competition = competitionRepository.findOne(competitionId);
//		Set<Player> playersCandidates = competition.getPlayers();
		List<Player> playersCandidates = playerRepository.findByActiveOrderByPointsDesc(true);
		
//		Seed players determination is put on hold, no solution at the moment; possible hibernate problem
// 		List is mocked for time being.
//		List<Player> seedPlayers = competitionRepository.findSeedPlayers(competitionId);
//		logger.info("seedPlayers " + seedPlayers);
//		List<Player> sortedPlayers = new ArrayList<Player>(new TreeSet<Player>(playersCandidates));
		
		// All players are temporary here, just for testing
//		List<Player> allPlayers = playerService.allClean();
		// Number of players and seed players are fine, continue
		if(utils.setupCompleted(competition, autogeneratedPlayers, autogeneratedSeeds)) {
			List<Game> gamesCheck = gameRepository.findByFinished(false);
			logger.info("gamesCheck.isEmpty() " + gamesCheck.isEmpty());
			if (!gamesCheck.isEmpty()) {
				throw new CompetitionNotCompletedException();
			}
			
			// determine groups
			List<Group> groups = utils.createGroups(competition, playersCandidates, autogeneratedPlayers, autogeneratedSeeds);
			competition.setGroups(groups);

			List<Game> games = utils.createGames(competition);
			competition.setGames(games);
		}
		
		for (CompetitionPlayer competitionPlayer : competition.getCompetitionPlayers()) {
			logger.info(competitionPlayer.toString());
		}
		
		// Za Gagu:
		// Ne znam da li je ovo resenje ili budzevina. Ostavicu ovako za sada uz komentar u README da bi trebalo pregeldati jos jednom pre finalne verzije.
		// Sacuvao sam prvo grupu, pa competition-player, a tek onda citav competition.
		List<Group> groups = competition.getGroups();
		logger.info("Groups " + groups);
		for (Group group : groups) {
			groupRepository.save(group);
			entityManager.flush();
		}
		Iterable<Group> groupsIter = groupRepository.saveAll(groups);
//		competition.setGroups((List<Group>)groupsIter);

		Iterable<CompetitionPlayer> iter = competition.getCompetitionPlayers();
		Iterable<CompetitionPlayer> cp = competitionPlayerRepository.saveAll(iter);
//		competition.setCompetitionPlayers((List<CompetitionPlayer>)cp);
		
		
		competition = competitionRepository.save(competition);
		
		return competition;
	}

}
