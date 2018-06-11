package com.ds.microservices.sport.tabletennis.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:competition-default.properties")
public class CompetitionConfiguration {

	protected Logger logger = Logger.getLogger(CompetitionConfiguration.class.getName());

	public final static String FORMAT = "CUP";
	public final static int NUMBER_OF_PLAYERS = 32;
	public final static int NUMBER_OF_SEEDS = 8;

}
