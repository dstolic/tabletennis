package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionNotCorrectNumberOfPlayersException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionNotCorrectNumberOfPlayersException() {
		super("Number of players in competition is not correct.");
	}

}
