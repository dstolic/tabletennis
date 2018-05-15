package com.ds.microservices.sport.tabletennis.start;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.ds.microservices.sport.tabletennis.config.DataConfiguration;;


@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(DataConfiguration.class)
public class PlayersServer {
	
//	@Autowired
//	protected PlayerRepository playerRepository;
//
//	@Autowired
//	protected LeaguesRepository leagueRepository;
//
//	@Autowired
//	protected LeaguePlayerRepository leaguePlayerRepository;

//	@Bean
//	public LeagueService leagueService() {
//		return new LeagueService(leagueRepository);
//	}
//
//	@Bean
//	public LeagueController leagueController() {
//		return new LeagueController(leagueService(), leagueRepository);
//	}
//
//	@Bean
//	public PlayerService playerService() {
//		return new PlayerService(playerRepository);
//	}
//
//	@Bean
//	public LeagueMapper playerService() {
//		return new LeagueM;
//	}
//
//
//	@Bean
//	public PlayerController playerController() {
//		return new PlayerController(playerService(), playerRepository);
//	}


	protected Logger logger = Logger.getLogger(PlayersServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name" , "players-server");
		SpringApplication.run(PlayersServer.class, args);
	}

}
