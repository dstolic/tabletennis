package com.ds.microservices.sport.tabletennis.util;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotFoundException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotCorrectNumberOfPlayersException;

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
		logger.info("createGroups START");
		
		int numberOfSeeds = competitionConfiguration.getNumberOfSeeds();
		int numberOfPlayers = competitionConfiguration.getNumberOfPlayers();
		int numberOfGroups = numberOfPlayers / numberOfSeeds ;
		List<Group> groups = new ArrayList<>();

		int addedPlayers = CollectionUtils.isEmpty(competition.getCompetitionPlayers()) ? 0 : competition.getCompetitionPlayers().size();
		int totalPlayers = competitionConfiguration.getNumberOfPlayers();
		int missingPlayers = totalPlayers - addedPlayers;
		
		// Add players to competition according to their points (first 'number_of_players')
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
		List<CompetitionPlayer> sortedPlayers = competition.getCompetitionPlayers();
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
			for (int j = drawStart; j <= drawEnd; j++) {
				indexes.add(Long.valueOf(j));
			}
			
			// number_of_seeds 0-7
			for (int j = 0; j < numberOfSeeds; j++) {
				int randomIndex = ThreadLocalRandom.current().nextInt(0, numberToDraw);
				CompetitionPlayer drawPlayer = sortedPlayers.get(indexes.get(randomIndex).intValue());
				drawPlayer.setActive(true);
				groups.get(numberToDraw-1).getPlayers().add(drawPlayer.getId().getPlayer());
				indexes.remove(randomIndex);
				numberToDraw--;
			}
			
		}
		
		checkBalanceOfPower(groups);
		logger.info("createGroups END");
		return groups;
	}

	// Checking group coef (balance of power: proportion between max and min sum of players points)
	// If it is too high, groups are not balanced. 
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
	
	
	// Round robin tournament games. Check if this may be double round robin.
	public List<Game> generateGamesForGroup(Competition competition, Group group) {
		logger.info("generateGamesForGroup START");
		
		List<Game> games = new ArrayList<>();

		int numberOfPlayers = group.getPlayers().size();
		// if we have odd number of players, we'll add 1 (bye)
		if (numberOfPlayers % 2 == 1) {
			numberOfPlayers++;
		}
		int numberOfRounds = numberOfPlayers - 1;
//		int numberOfGames = numberOfPlayers * numberOfRounds;

		List<Player> first = new ArrayList<>();
		List<Player> second = new ArrayList<>();

		for (int i = 0; i < numberOfPlayers; i++) {
			if (i < numberOfPlayers / 2) {
				first.add(group.getPlayers().get(i));
			} else {
				second.add(0, group.getPlayers().get(i));
			}
		}

		for (int round = 1; round <= numberOfRounds; round++) {
			// Round x
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

		}

		logger.info("generateGamesForGroup END");
		return games;
	}

	public void generateSecondPart(Competition competition) {
		logger.info("competition-util generateSecondPart() ");

		// temporary, for testing
		generateResults(competition);
//		groupSort(competition);
	}

//	public void groupSort(Competition competition) {
//		logger.info("groupSort  ");
//		List<Group> groups = competition.getGroups();
//		for (Group group : groups) {
//			logger.info("group  " + group);
//		}
//
//	}
	

	
	public void generateResults(Competition competition) {
//		competition.getGames().forEach(game -> generateGameResult(game));
		competition.getGames().forEach(this::generateGameResult);
	}
	
	public void generateGameResult(Game game) {
		Player home = game.getPlayerHome();
		Player away = game.getPlayerAway();
		
		logger.info("Home " + home.getFirstName() + " " + home.getLastName() + "(" + home.getPoints() + ")");
		logger.info("Away " + away.getFirstName() + " " + away.getLastName() + "(" + away.getPoints() + ")");
		
//		int hPoints = home.getPoints().intValue();
//		int aPoints = away.getPoints().intValue();
		
//		int numberToDraw = hPoints + aPoints;
//		int randomIndex = ThreadLocalRandom.current().nextInt(0, numberToDraw);
		
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

//		logger.info("Sets home 1: " + Arrays.toString(set_points_home) );
//		logger.info("Sets away 1: " + Arrays.toString(set_points_away) );

		if (game.getPointsAway() > game.getPointsHome()) {
			logger.info("Game away win: " + away.getFirstName() + " " + away.getLastName() + " won " + game.getPointsHome() + " : " + game.getPointsAway() );
		} else {
			logger.info("Game home win: " + home.getFirstName() + " " + home.getLastName() + " won " + game.getPointsHome() + " : " + game.getPointsAway() );
		}

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

	
	// TODO: activate competition (check first if current competition may be finished) 
	// Check if competition has all elements for generating games (players, seeds...)
	public boolean activateCheck(Competition competition) {
		
		// Check number of players
		boolean setupCompleted = ((competition.getCompetitionPlayers() == null) ? 0 : competition.getCompetitionPlayers().size()) == competitionConfiguration.getNumberOfPlayers();
		if (!setupCompleted) throw new CompetitionNotCorrectNumberOfPlayersException();
		
		// Check number of seeds - 8
		//		completed = (competition.getPlayers().size() == Long.parseLong(map.get("NUMBER_OF_SEEDS").getValue()))

		// Check number of games - 0
		setupCompleted = competition.getGames().isEmpty();
		if (!setupCompleted) throw new CompetitionNotCorrectNumberOfPlayersException();

	
		logger.log(Level.INFO, "Setup completed {0}", setupCompleted);
		return setupCompleted;
	}

	public static void main(String[] args) {

		int rounds = 5;
		int numberOfPlayers = 6;
		
		List<Integer> first = new ArrayList<>();
		List<Integer> second = new ArrayList<>();

		for (int i = 1; i <= numberOfPlayers; i++) {
			if (i <= numberOfPlayers / 2) {
				first.add(Integer.valueOf(i));
			} else {
				second.add(0, Integer.valueOf(i));
			}
		}

		for (int x = 1; x <= rounds; x++) {
			// Round x
			// first arrays transformation
			// remove elements
			Integer moveFirst = first.get(first.size()-1);
			first.remove(first.size()-1);
			Integer moveSecond = second.get(0);
			second.remove(0);
			// add elements
			first.add(1, moveSecond);
			second.add(moveFirst);

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