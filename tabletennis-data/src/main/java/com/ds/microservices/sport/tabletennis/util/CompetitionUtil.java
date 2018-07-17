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
	
	// Check if competition has all elements for generating games (players, seeds...)
	public boolean setupCompleted(Competition competition) {
		
		boolean setupCompleted = true;

		// Check number of players
		if (!competitionConfiguration.isAutogeneratePlayers()) {
			setupCompleted = ((competition.getCompetitionPlayers() == null) ? 0 : competition.getCompetitionPlayers().size()) == competitionConfiguration.getNumberOfPlayers();
			if (!setupCompleted) throw new CompetitionNotCorrectNumberOfPlayersException();
		}
		
		// Check number of seeds - 8
		//		completed = (competition.getPlayers().size() == Long.parseLong(map.get("NUMBER_OF_SEEDS").getValue()))

		// Check number of games - 0
		setupCompleted = competition.getGames() != null && !competition.getGames().isEmpty() ? true : false;
		if (!setupCompleted) throw new CompetitionNotCorrectNumberOfPlayersException();

	
		logger.info("Setup completed " + setupCompleted);
		return setupCompleted;
	}

	public List<Group> createGroups(Competition competition, List<Player> candidates) {
		logger.info("createGroups START");
		
		int numberOfSeeds = competitionConfiguration.getNumberOfSeeds();
		int numberOfPlayers = competitionConfiguration.getNumberOfPlayers();
		int numberOfGroups = numberOfPlayers / numberOfSeeds ;
		List<Group> groups = new ArrayList<Group>();

		int addedPlayers = CollectionUtils.isEmpty(competition.getCompetitionPlayers()) ? 0 : competition.getCompetitionPlayers().size();
		int totalPlayers = competitionConfiguration.getNumberOfPlayers();
		int missingPlayers = totalPlayers - addedPlayers;
		
		logger.info("missingPlayers " + missingPlayers);
		
		// Add players to competition according to their points (first 'number_of_players')
		boolean noMorePlayers = false;
		if(missingPlayers > 0 || noMorePlayers) {
			if (competitionConfiguration.isAutogeneratePlayers()) {
				for (Player player : candidates) {
					if (player.isActive() && missingPlayers > 0) {
						if (!this.getCompetitionPlayerMap().containsKey(new CompetitionPlayerPK(competition, player))) {
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
				noMorePlayers = true;
			}
		}
		
		logger.log(Level.INFO, "NUMBER_OF_PLAYERS after {0}", competition.getCompetitionPlayers().size());
		
		competition.getCompetitionPlayers().forEach(competitionPlayer ->
			logger.info("CompetitionPlayer " + competitionPlayer.getId().getPlayer())
		);

		// Create players pools 
		logger.info("numberOfGroups " + numberOfGroups);
//		List<Group> drawingGroups = new ArrayList<Group>(); 
//		for (int i = 1; i <= number_of_groups; i++) {
//			Group group = new Group();
//			drawingGroups.add(group);
//		}
		
		// Add seed players
		List<CompetitionPlayer> sortedPlayers = competition.getCompetitionPlayers();
		logger.info("sortedPlayers " + sortedPlayers.size());
		logger.info("numberOfSeeds " + numberOfSeeds);
		char groupName = 'A';
		for (int i = 1; i <= numberOfSeeds; i++) {
			Group group = new Group();
			Long groupId = new Long(i);

			List<Player> groupPlayers = new ArrayList<Player>();
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
		
		// Show seed players
		for (Group group : groups) {
			logger.info("group " + group);
		}

		// Draw
		int draw_start = 1;
		int draw_end = 1;
		
		// number_of_groups 0-2
		// indexes: 0-7 (seeds, already covered), 8-15, 16-23, 24-31
		// number_to_draw = 8
		for (int i = 0; i < numberOfGroups-1; i++) {
			int numberToDraw = numberOfSeeds;
			ArrayList<Long> indexes = new ArrayList<Long>(); 
			draw_start = (numberOfSeeds * (i+1));
			draw_end = (numberOfSeeds * (i+2))-1;
			logger.info("draw (" + draw_start + " : " + draw_end + ")");
			for (int j = draw_start; j <= draw_end; j++) {
				indexes.add(new Long(j));
			}
			logger.info("indexes " + indexes);
			
			// number_of_seeds 0-7
			for (int j = 0; j < numberOfSeeds; j++) {
				logger.info("indexes at work " + indexes);
				int randomIndex = ThreadLocalRandom.current().nextInt(0, numberToDraw);
				logger.info("randomIndex " + randomIndex);
				CompetitionPlayer drawPlayer = sortedPlayers.get(indexes.get(randomIndex).intValue());
//				drawPlayer.setGroup(groups.get(number_to_draw-1));
				drawPlayer.setActive(true);
				groups.get(numberToDraw-1).getPlayers().add(drawPlayer.getId().getPlayer());
				indexes.remove(randomIndex);
				numberToDraw--;
			}
			
		}
		
		// Checking group coef (balance of power of the groups)
		float minPoints = 9999;
		float maxPoints = 0;
		for (Group group : groups) {
			if(minPoints > group.groupPoints()) minPoints =  group.groupPoints();
			if(maxPoints < group.groupPoints()) maxPoints =  group.groupPoints();
			logger.info("group " + group);
		}
		logger.info("group coef " + (maxPoints / minPoints));


		logger.info("createGroups END");
		return groups;
	}

	public List<Game> createGames(Competition competition) {
		List<Game> games = new ArrayList<Game>();
		
		for (int i = 0; i < competition.getGroups().size(); i++) {
			games.addAll(generateGamesForGroup(competition, competition.getGroups().get(i)));
		}
		
		
		return games;
	}
	
	
	// Round robin tournament games. Check if this may be double round robin.
	public List<Game> generateGamesForGroup(Competition competition, Group group) {
		logger.info("generateGamesForGroup START");
		
		List<Game> games = new ArrayList<Game>();

		int number_of_players = group.getPlayers().size();
		// if we have odd number of players, we'll add 1 (bye)
		if (number_of_players % 2 == 1) {
			number_of_players++;
		}
		int number_of_rounds = number_of_players - 1;
		int number_of_games = number_of_players * number_of_rounds;

		logger.info("number_of_players " + number_of_players);
		logger.info("number_of_rounds " + number_of_rounds);
		logger.info("number_of_games " + number_of_games);

		ArrayList<Player> first = new ArrayList<Player>();
		ArrayList<Player> second = new ArrayList<Player>();

		for (int i = 0; i < number_of_players; i++) {
			if (i < number_of_players / 2) {
				first.add(group.getPlayers().get(i));
			} else {
				second.add(0, group.getPlayers().get(i));
			}
		}

		for (int round = 1; round <= number_of_rounds; round++) {
			// Round x
			System.out.println("Before " + round + ": first " + first);
			System.out.println("Before " + round + ": second" + second);
			System.out.println("Round " + round);
			for (int i = 0; i < number_of_players/2; i++) {
				logger.info("" + first.get(i) + " : " + second.get(i));

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
			Player move_first = first.get(first.size()-1);
			first.remove(first.size()-1);
			Player move_second = second.get(0);
			second.remove(0);
			// add elements
			first.add(1, move_second);
			second.add(move_first);

		}

		
		for (Game game : games) {
			logger.info("Game : " + game);
		}
		
		
		
		
		
		logger.info("generateGamesForGroup END");
		return games;
	}

	public void generateSecondPart(Competition competition) {
		logger.info("competition-util generateSecondPart() ");

		logger.info("GROUPS  " + competition.getGroups().size());
		logger.info("PLAYERS " + competition.getCompetitionPlayers().size());
		logger.info("GAMES   " + competition.getGames().size());
		
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
		logger.info("GAMES   " + competition.getGames().size());
		
		for (Game game : competition.getGames()) {
//			if (game.getGroupId() == 98) {
				generateGameResult(game);
//			}
		}
	}
	
	public void generateGameResult(Game game) {
		Player home = game.getPlayerHome();
		Player away = game.getPlayerAway();
		
		logger.info("Home " + home.getFirstName() + " " + home.getLastName() + "(" + home.getPoints() + ")");
		logger.info("Away " + away.getFirstName() + " " + away.getLastName() + "(" + away.getPoints() + ")");
		
		int hPoints = home.getPoints().intValue();
		int aPoints = away.getPoints().intValue();
		
		int number_to_draw = hPoints + aPoints;
		int randomIndex = ThreadLocalRandom.current().nextInt(0, number_to_draw);
		logger.info("randomIndex " + randomIndex);
		
		int set_points_home[] = new int[5];
		int set_points_away[] = new int[5];
		int sets_home = 0;
		int sets_away = 0;
		for (int i = 0; i < 5 && sets_home < 3  && sets_away < 3; i++) {
			int random_home = ThreadLocalRandom.current().nextInt(0, 11);
			int random_away = ThreadLocalRandom.current().nextInt(0, 11);
			if(random_home > random_away) {
				sets_home++;
				if(11 > random_away + 2) {
					set_points_home[i] = 11;
					set_points_away[i] = random_away;
				} else {
					set_points_home[i] = random_away + 2;
					set_points_away[i] = random_away;
				}
			} else {
				sets_away++;
				if(11 > random_home + 2) {
					set_points_home[i] = random_home;
					set_points_away[i] = 11;
				} else {
					set_points_home[i] = random_home;
					set_points_away[i] = random_home + 2;
				}
			}
		}

		game.setPointsHome(sets_home);
		game.setPointsAway(sets_away);
		
		List<GameSet> sets = new ArrayList<GameSet>();
		game.setSets(sets);
		for (int i = 0; i < set_points_away.length; i++) {
			if (!(set_points_home[i] == 0 && set_points_away[i] == 0)) {
				GameSet gameSet = new GameSet();
				GameSetId gameSetId = new GameSetId();
				gameSetId.setSetNo(new Long(i+1));
				gameSetId.setGame(game);
				gameSet.setId(gameSetId);
				gameSet.setPointsHome(set_points_home[i]);
				gameSet.setPointsAway(set_points_away[i]);
				sets.add(gameSet);
			}
		}

		logger.info("Sets home 1: " + Arrays.toString(set_points_home) );
		logger.info("Sets away 1: " + Arrays.toString(set_points_away) );

		if (game.getPointsAway() > game.getPointsHome()) {
			logger.info("Game away win: " + away.getFirstName() + " " + away.getLastName() + " won " + game.getPointsHome() + " : " + game.getPointsAway() );
		} else {
			logger.info("Game home win: " + home.getFirstName() + " " + home.getLastName() + " won " + game.getPointsHome() + " : " + game.getPointsAway() );
		}

	}

	
	// Check if competition has all elements for generating games (players, seeds...)
	public boolean generateCheck(Optional<Competition> competition) {
		
		checkIfEmpty(competition);
		
		checkIfInProgress(competition);
		
		checkIfCompleted(competition);
		
		checkNumberOfPlayers(competition.get().getCompetitionPlayers());
		
		return true;
	}
	
	private void checkIfEmpty(Optional<Competition> competition) {
		
		if (!competition.isPresent() || competition.get().getId() == null) {
			 throw new CompetitionNotFoundException();
		}

	}

	private void checkIfInProgress(Optional<Competition> competition) {

		if (!competition.get().isCurrent() || competition.get().isCompleted()) {
			 throw new CompetitionAlreadyCompletedException();
		}

	}
	
	private void checkIfCompleted(Optional<Competition> competition) {

		if (competition.isPresent() && competition.get().isCompleted()) {
			 throw new CompetitionAlreadyCompletedException();
		}

	}
	
	private void checkNumberOfPlayers(List<CompetitionPlayer> competitionPlayers) {

		// Check number of players
		if (!competitionConfiguration.isAutogeneratePlayers()) {
			if(competitionPlayers == null || competitionPlayers.size() != competitionConfiguration.getNumberOfPlayers()) {
				throw new CompetitionNotCorrectNumberOfPlayersException();
			}
		}

	}
	
	// Check if competition has all elements for generating games (players, seeds...)
	public boolean activateCheck(Competition competition) {
		
//		boolean setupCompleted = false;
		
		// Check number of players
		boolean setupCompleted = ((competition.getCompetitionPlayers() == null) ? 0 : competition.getCompetitionPlayers().size()) == competitionConfiguration.getNumberOfPlayers();
		if (!setupCompleted) throw new CompetitionNotCorrectNumberOfPlayersException();
		
		// Check number of seeds - 8
		//		completed = (competition.getPlayers().size() == Long.parseLong(map.get("NUMBER_OF_SEEDS").getValue()))

		// Check number of games - 0
		setupCompleted = competition.getGames() != null && competition.getGames().size() > 0 ? true : false;
		if (!setupCompleted) throw new CompetitionNotCorrectNumberOfPlayersException();

	
		logger.log(Level.INFO, "Setup completed {0}", setupCompleted);
		return setupCompleted;
	}

	public static void main(String[] args) {

		int rounds = 5;
		int number_of_players = 6;
		
		ArrayList<Integer> first = new ArrayList<Integer>();
		ArrayList<Integer> second = new ArrayList<Integer>();

		for (int i = 1; i <= number_of_players; i++) {
			if (i <= number_of_players / 2) {
				first.add(new Integer(i));
			} else {
				second.add(0, new Integer(i));
			}
		}

		for (int x = 1; x <= rounds; x++) {
			// Round x
			System.out.println("Before " + x + ": " + first + ", " + second);
			System.out.println("Round " + x);
			for (int i = 0; i < number_of_players/2; i++) {
				System.out.println("" + first.get(i) + " : " + second.get(i));
			}
	
			// first arrays transformation
			// remove elements
			Integer move_first = first.get(first.size()-1);
			first.remove(first.size()-1);
			Integer move_second = second.get(0);
			second.remove(0);
			// add elements
			first.add(1, move_second);
			second.add(move_first);

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