package com.ds.microservices.sport.tabletennis.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:competition-default.properties")
public class CompetitionConfiguration {

	protected Logger logger = Logger.getLogger(CompetitionConfiguration.class.getName());

	@Value("${competition.default.FORMAT}")
	public String FORMAT ;
	
	@Value("${competition.default.NUMBER_OF_PLAYERS}")
	public int NUMBER_OF_PLAYERS;
	
	@Value("${competition.default.NUMBER_OF_SEEDS}")
	public int NUMBER_OF_SEEDS;

	@Value("${competition.default.TYPE}")
	public String TYPE;

	public String getFORMAT() {
		return FORMAT;
	}

	public void setFORMAT(String fORMAT) {
		FORMAT = fORMAT;
	}

	public int getNUMBER_OF_PLAYERS() {
		return NUMBER_OF_PLAYERS;
	}

	public void setNUMBER_OF_PLAYERS(int nUMBER_OF_PLAYERS) {
		NUMBER_OF_PLAYERS = nUMBER_OF_PLAYERS;
	}

	public int getNUMBER_OF_SEEDS() {
		return NUMBER_OF_SEEDS;
	}

	public void setNUMBER_OF_SEEDS(int nUMBER_OF_SEEDS) {
		NUMBER_OF_SEEDS = nUMBER_OF_SEEDS;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

}
