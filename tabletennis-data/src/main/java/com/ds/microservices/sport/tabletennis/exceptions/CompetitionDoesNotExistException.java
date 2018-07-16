package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionDoesNotExistException() {
		super("Competition does not exist.");
	}

}
