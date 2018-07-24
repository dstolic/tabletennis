package com.ds.microservices.sport.tabletennis.report.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:competition-default.properties")
public class CompetitionConfiguration {

	protected Logger logger = Logger.getLogger(CompetitionConfiguration.class.getName());

	// For future use. 
	// Default value is 'CUP'. Competition format may be also 'LEAGUE' and 'LEAGUE+CUP'
	@Value("${competition.default.format}")
	private String format ;
	
	@Value("${competition.default.number.players}")
	private int numberOfPlayers = 32;
	
	@Value("${competition.default.number.seeds}")
	private int numberOfSeeds;

	// Level of tournaments. Expected values are 'SENIOR' and 'MEDIOR'
	@Value("${competition.default.category}")
	private String category;

	// Set parameter to 'true' if you want to add all or some of seed players to competition automatically.
	// If value is 'false', app expects to enter all seed players manually.
	@Value("${competition.autogenerate.seeds}")
	private boolean autogenerateSeeds;
	
	// Set parameter to 'true' if you want to add all or some of players to competition automatically.
	// If value is 'false', app expects to enter all players manually.
	@Value("${competition.autogenerate.players}")
	private boolean autogeneratePlayers;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getNumberOfSeeds() {
		return numberOfSeeds;
	}

	public void setNumberOfSeeds(int numberOfSeeds) {
		this.numberOfSeeds = numberOfSeeds;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isAutogenerateSeeds() {
		return autogenerateSeeds;
	}

	public void setAutogenerateSeeds(boolean autogenerateSeeds) {
		this.autogenerateSeeds = autogenerateSeeds;
	}

	public boolean isAutogeneratePlayers() {
		return autogeneratePlayers;
	}

	public void setAutogeneratePlayers(boolean autogeneratePlayers) {
		this.autogeneratePlayers = autogeneratePlayers;
	}
	

}
