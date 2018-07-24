package com.ds.microservices.sport.tabletennis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ds.microservices.sport.tabletennis.config.CompetitionConfiguration;
import com.ds.microservices.sport.tabletennis.config.DataConfiguration;
import com.ds.microservices.sport.tabletennis.entity.Competition;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayer;
import com.ds.microservices.sport.tabletennis.entity.CompetitionPlayerPK;
import com.ds.microservices.sport.tabletennis.entity.Game;
import com.ds.microservices.sport.tabletennis.entity.GameSet;
import com.ds.microservices.sport.tabletennis.entity.GameSetId;
import com.ds.microservices.sport.tabletennis.entity.Group;
import com.ds.microservices.sport.tabletennis.entity.Player;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionAlreadyCompletedException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotCompletedException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotCorrectNumberOfPlayersException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotFoundException;
import com.ds.microservices.sport.tabletennis.exceptions.GamesNotGeneratedException;

@Component
public class CompetitionUtil {

	@Autowired
	DataConfiguration dataConfiguration;
 
	@Autowired
	CompetitionConfiguration competitionConfiguration;
 
	protected Logger logger = Logger.getLogger(CompetitionUtil.class.getName());

	private Map<CompetitionPlayerPK, CompetitionPlayer> competitionPlayerMap;
    
	public void init(Competition competition) {
		createCompetitionPlayersMap(competition);
	}
	
	public List<Group> createGroups(Competition competition, List<Player> candidates) {
		int numberOfSeeds = competitionConfiguration.getNumberOfSeeds();
		int numberOfPlayers = competitionConfiguration.getNumberOfPlayers();
		int numberOfGroups = numberOfPlayers / numberOfSeeds ;
		List<Group> groups = new ArrayList<>();

		// we are able to add players to competition manually, the rest of them well be added automaticaly
		int addedPlayers = CollectionUtils.isEmpty(competition.getCompetitionPlayers()) ? 0 : competition.getCompetitionPlayers().size();
		// number of players needed for competition (read from configuration)  
		int totalPlayers = competitionConfiguration.getNumberOfPlayers();
		// we will add as many players as needed to reach totalPlayers  
		int missingPlayers = totalPlayers - addedPlayers;
		
		// Add players to competition according to their points (first 'number_of_players')
		// Configuration parameter 'autogeneratePlayers=true' allowing us to automatically add players to competition
		// Add active players to CompetitionPlayer list until missing players is 0
		if (missingPlayers > 0 && competitionConfiguration.isAutogeneratePlayers()) {
			for (Player player : candidates) {
				if ((player.isActive() && missingPlayers > 0) && !this.getCompetitionPlayerMap().containsKey(new CompetitionPlayerPK(competition, player))) {
					CompetitionPlayer competitionPlayer = new CompetitionPlayer();
					CompetitionPlayerPK pk = new CompetitionPlayerPK(competition, player);
					competitionPlayer.setId(pk);
					
					this.getCompetitionPlayerMap().put(competitionPlayer.getId(), competitionPlayer);
					competition.getCompetitionPlayers().add(competitionPlayer);
					missingPlayers--;
					logger.log(Level.INFO, "missingPlayers changed {0}. Added {1}", new Object[]{missingPlayers, player.getPoints()});
				}
			}
		}
		
		// Add seed players
		// Players are sorted by rating points. 
		// Add first 'numberOfSeeds'(8 by default) players each of them to next group 
		List<CompetitionPlayer> sortedPlayers = competition.getCompetitionPlayers();
		// Groups are named 'A'-'H' (8 groups by default)
		char groupName = 'A';
		for (int i = 1; i <= numberOfSeeds; i++) {
			Group group = new Group();
			List<Player> groupPlayers = new ArrayList<>();
			CompetitionPlayer competitionPlayer = sortedPlayers.get(i-1);
			competitionPlayer.setSeed(true);
			competitionPlayer.setActive(true);
			
			groupPlayers.add(competitionPlayer.getId().getPlayer());
			group.setPlayers(groupPlayers);
			group.setName(String.valueOf(groupName));
			groupName++;
			group.setCompetition(competition);
			
			groups.add(group);
		}

		
		// Draw
		// Add other members of the groups.
		// Members are divided by ration points to 3 groups (seeds are already added to groups)
		int drawStart = 1;
		int drawEnd = 1;
		
		// number_of_groups 0-2
		// indexes: 0-7 (seeds, already covered), 8-15, 16-23, 24-31
		// number_to_draw = 8
		for (int i = 0; i < numberOfGroups-1; i++) {
			int numberToDraw = numberOfSeeds;
			ArrayList<Long> indexes = new ArrayList<>(); 
			drawStart = (numberOfSeeds * (i+1));
			drawEnd = (numberOfSeeds * (i+2))-1;
			// indexes as array
			// by default we are choosing 3 groups of players
			// so values for 'indexes' will be as above: [8..15], [16..23], [24..31]
			for (int j = drawStart; j <= drawEnd; j++) {
				indexes.add(Long.valueOf(j));
			}
			
			// number_of_seeds 0-7
			for (int j = 0; j < numberOfSeeds; j++) {
				int randomIndex = ThreadLocalRandom.current().nextInt(0, numberToDraw);
				// Draw random player index (0-7), add player with that index to group and then remove that index from 'indexes' array
				CompetitionPlayer drawPlayer = sortedPlayers.get(indexes.get(randomIndex).intValue());
				drawPlayer.setActive(true);
				groups.get(numberToDraw-1).getPlayers().add(drawPlayer.getId().getPlayer());
				indexes.remove(randomIndex);
				numberToDraw--;
			}
			
		}
		
		checkBalanceOfPower(groups);
		return groups;
	}

	// Checking group coef (balance of power: proportion between max and min sum of players points)
	// If it is too high, groups are not balanced. 
	// At this moment, there will be only one line in log to show balance
	// For future use: possible usage is after competition generation, so you may cancel it if balance is too high (not balanced groups)
	public void checkBalanceOfPower(List<Group> groups) {
		float minPoints = 9999;
		float maxPoints = 0;
		for (Group group : groups) {
			if(minPoints > group.groupPoints()) minPoints =  group.groupPoints();
			if(maxPoints < group.groupPoints()) maxPoints =  group.groupPoints();
		}
		logger.log(Level.INFO, "group coef {0}", (maxPoints / minPoints));
	}
	
	public List<Game> createGames(Competition competition) {
		List<Game> games = new ArrayList<>();
		
		for (int i = 0; i < competition.getGroups().size(); i++) {
			games.addAll(generateGamesForGroup(competition, competition.getGroups().get(i)));
		}
		
		
		return games;
	}
	
	
	// Round robin tournament games. Check later if this may be double round robin.
	public List<Game> generateGamesForGroup(Competition competition, Group group) {
		logger.info("generateGamesForGroup START");
		
		List<Game> games = new ArrayList<>();

		int numberOfPlayers = group.getPlayers().size();
		// if we have odd number of players, we'll add 1 ('BYE': when you are paired with BYE, you'll have pause in that round)
		if (numberOfPlayers % 2 == 1) {
			numberOfPlayers++;
		}
		int numberOfRounds = numberOfPlayers - 1;

/* 
 *    Small modification of Berger system to generate all games for group of players
 *    Default example: we divide list of group players in two lists. First player from first list is fixed, he will not change position. 
 *    In default case: [0,1], [3,2]. Pairs that make games are determined by index: (0, 3), (1, 2)
 *    Then, we'll transform initial lists.
 *     TRANSFORMATION first list: first player for second list -> second player in first list (other members of first list shifts to the right).
 *    TRANSFORMATION second list: last one from first list -> last one in second list (other members of second list shifts to the left)
 *    Example with 14 players is more descriptive:
 *     First list: { 1,  2,  3,  4,  5,  6,  7} 
 *    Second list: {14, 13, 12, 11, 10,  9,  8} 
 *    Games are (1, 14), (2, 13) and so on... 
 *    After first transformation: 
 *     First list: { 1, 14,  2,  3,  4,  5,  6} 
 *    Second list: {13, 12, 11, 10,  9,  8,  7} 
 *    Games are (1, 13), (14, 12) and so on...		 
 */

		List<Player> first = new ArrayList<>();
		List<Player> second = new ArrayList<>();

		for (int i = 0; i < numberOfPlayers; i++) {
			if (i < numberOfPlayers / 2) {
				first.add(group.getPlayers().get(i));
			} else {
				second.add(0, group.getPlayers().get(i));
			}
		}
		logger.log(Level.INFO, " First initial {0}", first);
		logger.log(Level.INFO, "Second initial {0}", second);
		
		for (int round = 1; round <= numberOfRounds; round++) {
			for (int i = 0; i < numberOfPlayers/2; i++) {
				Game game = new Game();
				game.setPlayerHome(first.get(i));
				game.setPlayerAway(second.get(i));
				game.setGroupId(group.getId());
				game.setRound(round);
				game.setCompetition(competition);
				
				games.add(game);
			}
	
			// first arrays transformation
			// remove elements
			Player moveFirst = first.get(first.size()-1);
			first.remove(first.size()-1);
			Player moveSecond = second.get(0);
			second.remove(0);
			// add elements
			first.add(1, moveSecond);
			second.add(moveFirst);

			logger.log(Level.INFO, " First after round {0}: {1}", new Object[]{round, first});
			logger.log(Level.INFO, "Second after round {0}: {1}", new Object[]{round, second});
		}

		logger.info("generateGamesForGroup END");
		return games;
	}

	public void generateSecondPart(Competition competition) {
		logger.info("competition-util generateSecondPart() ");

		// temporary, for testing
		generateResults(competition);
	}

	public void generateResults(Competition competition) {
		competition.getGames().forEach(this::generateGameResult);
	}
	
	
	
	// Check if competition has all elements for generating games (players, seeds...)
	public boolean checkIfCompetitionGenerateIsAllowed(Optional<Competition> competition) {
		
		checkIfEmpty(competition);
		
		checkIfCompleted(competition);
		
		checkNumberOfPlayers(competition.get().getCompetitionPlayers());
		
		checkNumberOfGames(competition.get().getGames());
		
		return true;
	}
	
	// If competiton not found 
	private void checkIfEmpty(Optional<Competition> competition) {
		if (!competition.isPresent() ) throw new CompetitionNotFoundException();
	}

	// If competiton is completed
	private void checkIfCompleted(Optional<Competition> competition) {
		if (competition.isPresent() && competition.get().isCompleted()) throw new CompetitionAlreadyCompletedException();
	}
	
	// If number of players in competiton is not correct (different from value in configuration: NUMBER_OF_PLAYERS)
	private void checkNumberOfPlayers(List<CompetitionPlayer> competitionPlayers) {
		if (!competitionConfiguration.isAutogeneratePlayers() 
			&& (competitionPlayers == null || (competitionPlayers.size() != competitionConfiguration.getNumberOfPlayers()) ) 
		) {
				throw new CompetitionNotCorrectNumberOfPlayersException();
		}
	}
	
	// If already exists games in competiton
	private void checkNumberOfGames(List<Game> games) {
		if(games != null && !games.isEmpty()) throw new CompetitionNotCompletedException();
	}

	
	// Check if competition has all elements for generating games (players, seeds...)
	public boolean checkIfCompetitionActivationIsAllowed(Optional<Competition> competition) {
		
		checkIfEmpty(competition);
		
		checkIfCompleted(competition);
		
		checkNumberOfPlayers(competition.get().getCompetitionPlayers());
		
		checkIfGamesExist(competition.get().getGames());
		
		return true;
	}
	
	// If already exists games in competiton
	private void checkIfGamesExist(List<Game> games) {
		if(games == null || games.isEmpty()) throw new GamesNotGeneratedException();
	}

	// For testing: delete in final version
	public void generateGameResult(Game game) {
		Player home = game.getPlayerHome();
		Player away = game.getPlayerAway();
		
		int[] setPointsHome = new int[5];
		int[] setPointsAway = new int[5];
		int setsHome = 0;
		int setsAway = 0;
		for (int i = 0; i < 5 && setsHome < 3  && setsAway < 3; i++) {
			int randomHome = ThreadLocalRandom.current().nextInt(0, 11);
			int randomAway = ThreadLocalRandom.current().nextInt(0, 11);
			if(randomHome > randomAway) {
				setsHome++;
				if(11 > randomAway + 2) {
					setPointsHome[i] = 11;
					setPointsAway[i] = randomAway;
				} else {
					setPointsHome[i] = randomAway + 2;
					setPointsAway[i] = randomAway;
				}
			} else {
				setsAway++;
				if(11 > randomHome + 2) {
					setPointsHome[i] = randomHome;
					setPointsAway[i] = 11;
				} else {
					setPointsHome[i] = randomHome;
					setPointsAway[i] = randomHome + 2;
				}
			}
		}

		game.setPointsHome(setsHome);
		game.setPointsAway(setsAway);
		
		List<GameSet> sets = new ArrayList<>();
		game.setSets(sets);
		for (int i = 0; i < setPointsAway.length; i++) {
			if (!(setPointsHome[i] == 0 && setPointsAway[i] == 0)) {
				GameSet gameSet = new GameSet();
				GameSetId gameSetId = new GameSetId();
				gameSetId.setSetNo(Long.valueOf(i+1));
				gameSetId.setGame(game);
				gameSet.setId(gameSetId);
				gameSet.setPointsHome(setPointsHome[i]);
				gameSet.setPointsAway(setPointsAway[i]);
				sets.add(gameSet);
			}
		}

	}

	
	public Map<CompetitionPlayerPK, CompetitionPlayer> getCompetitionPlayerMap() {
		return competitionPlayerMap;
	}

	public void setCompetitionPlayerMap(Map<CompetitionPlayerPK, CompetitionPlayer> competitionPlayerMap) {
		this.competitionPlayerMap = competitionPlayerMap;
	}

	public Map<CompetitionPlayerPK, CompetitionPlayer> createCompetitionPlayersMap(Competition competition) {
		if(this.competitionPlayerMap == null) {
			this.competitionPlayerMap = new ConcurrentHashMap<>();
			if(competition.getCompetitionPlayers() != null) {
				competition.getCompetitionPlayers().forEach(cp -> this.competitionPlayerMap.put(cp.getId(), cp));
			}
		}
		return competitionPlayerMap;
	}


}