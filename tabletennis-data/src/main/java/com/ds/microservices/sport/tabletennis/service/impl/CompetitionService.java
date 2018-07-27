package com.ds.microservices.sport.tabletennis.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.entity.Group;
import com.ds.microservices.sport.tabletennis.entity.Player;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotFoundException;
import com.ds.microservices.sport.tabletennis.exceptions.PlayerNotFoundException;
import com.ds.microservices.sport.tabletennis.repository.CompetitionPlayerRepository;
import com.ds.microservices.sport.tabletennis.repository.CompetitionRepository;
import com.ds.microservices.sport.tabletennis.repository.GameRepository;
import com.ds.microservices.sport.tabletennis.repository.GameSetRepository;
import com.ds.microservices.sport.tabletennis.repository.GroupRepository;
import com.ds.microservices.sport.tabletennis.repository.PlayerRepository;
import com.ds.microservices.sport.tabletennis.service.BaseCompetitionService;
import com.ds.microservices.sport.tabletennis.util.CompetitionUtil;

@Service
public class CompetitionService implements BaseCompetitionService {

	protected Logger logger = Logger.getLogger(CompetitionService.class.getName());

// Configuration and utilities
	@Autowired
	protected CompetitionConfiguration competitionConfiguration;

	@Autowired
	CompetitionUtil competitionUtil;
	
// Repositories
	@Autowired
	protected CompetitionRepository competitionRepository;
	
	@Autowired
	protected CompetitionPlayerRepository competitionPlayerRepository;

	@Autowired
	protected PlayerRepository playerRepository;

	@Autowired
	protected GameRepository gameRepository;

	@Autowired
	protected GameSetRepository gameSetRepository;

	@Autowired
	protected GroupRepository groupRepository;


/* 
 *  Common functions 
 */

	// List of all competitions
	@Override
	public List<Competition> allCompetitions() {
		return (List<Competition>)competitionRepository.findAll();
	}

	// Find competition by id
	@Override
	public Competition findById(Long id) {
		return competitionRepository.findById(id).orElseThrow(CompetitionNotFoundException::new);
	}

	// Find current competition
	@Override
	public Competition findByCurrent() {
		return competitionRepository.findByCurrent(true);
	}

/* 
 *  Edit functions (admin only) 
 */
		
	// Delete competition 
	@Override
	public void deleteCompetition(Long competitionId) {
		competitionRepository.deleteById(competitionId);
	}

	// Save/update competition
	@Override
	public Competition saveCompetition(Competition competition) {
		return competitionRepository.save(competition);
	}

	// Add player to current competition
	@Override
	public List<Player> addPlayerToCompetition(Long playerId) {
		Competition competition = competitionRepository.findByCurrent(true);
		Optional<Player> optPlayer = playerRepository.findById(playerId);
		
		if (competition == null) throw new CompetitionNotFoundException();
		if (!optPlayer.isPresent()) throw new PlayerNotFoundException();
		
		Player player = optPlayer.get();
		CompetitionPlayer newPlayer = competitionPlayerRepository.save(new CompetitionPlayer(competition, player));
		competition.getCompetitionPlayers().add(newPlayer);
		
		return playerRepository.findByActive(true);
	}
	
	// Add player to competition
	@Override
	public Competition addPlayerToCompetition(Long competitionId, Long playerId) {
		Competition competition = competitionRepository.findById(competitionId).orElseThrow(CompetitionNotFoundException::new);
		Player player = playerRepository.findById(playerId).orElseThrow(PlayerNotFoundException::new);

		CompetitionPlayer newPlayer = competitionPlayerRepository.save(new CompetitionPlayer(competition, player));
		
		competition.getCompetitionPlayers().add(newPlayer);
		
		return competition;
	}
	
	// Remove player from competition
	@Override
	public Competition removePlayerFromCompetition(Long competitionId, Long playerId) {
		Competition competition = competitionRepository.findById(competitionId).orElseThrow(CompetitionNotFoundException::new);
		Player player = playerRepository.findById(playerId).orElseThrow(PlayerNotFoundException::new);
		competition.getCompetitionPlayers().remove(new CompetitionPlayer(competition, player));

		return competitionRepository.save(competition);
	}
	
	// Generate competition
	@Override
	public Competition generateCompetition()  {
		Competition competition =  competitionRepository.findByCurrent(true);
		if (competition == null) throw new CompetitionNotFoundException();

		return generateCompetition(competition.getId());
	}	

	// Generate competition
	@Override
	public Competition generateCompetition(Long competitionId) {
		Competition competition = competitionRepository.findById(competitionId).orElseThrow(CompetitionNotFoundException::new);
		competitionUtil.init(competition);

		// Check if generate is allowed
		if(competitionUtil.checkIfCompetitionGenerateIsAllowed(Optional.of(competition))) {
			// create groups
			competition.setGroups(
					(List<Group>)groupRepository.saveAll(
							competitionUtil.createGroups(
									competition, 
									playerRepository.findByActiveOrderByPointsDesc(true)
							)
					)
			);

			// create games
			competition.setGames(
					competitionUtil.createGames(competition)
			);

		}
		
		competitionPlayerRepository.saveAll(competition.getCompetitionPlayers());
		
		return competitionRepository.save(competition);
	}


	@Override
	public Competition generateCompetition2(Long competitionId)  {
		Competition competition = competitionRepository.findById(competitionId).orElseThrow(CompetitionNotFoundException::new);

		competitionUtil.generateSecondPart(competition);
		
		return competition;
	}

	// Delete in final version
	@Override
	public Competition generateResults(Long competitionId) {
		Competition competition = findById(competitionId);

		competitionUtil.generateResults(competition);
		
		List<Game> games = competition.getGames();
		for (Game game : games) {
			gameSetRepository.saveAll(game.getSets());
			gameRepository.save(game);
		}

		return competition;
		
	}

	@Override
	public Competition generateCheck(Long competitionId) {
		Optional<Competition> optionalCompetition = competitionRepository.findById(competitionId);
		boolean check = competitionUtil.checkIfCompetitionGenerateIsAllowed(optionalCompetition);

		return optionalCompetition.orElseThrow(CompetitionNotFoundException::new);
	}


	@Override
	public Competition activateCheck(Long competitionId) {
		Optional<Competition> optionalCompetition = competitionRepository.findById(competitionId);

		boolean check = competitionUtil.checkIfCompetitionActivationIsAllowed(optionalCompetition);
		
		return optionalCompetition.orElseThrow(CompetitionNotFoundException::new);
	}

}
