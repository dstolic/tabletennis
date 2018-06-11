package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionNotEnoughPlayersException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionNotEnoughPlayersException() {
		super("Competition is not complete. Not enough players.");
	}

}
