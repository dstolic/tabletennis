package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionNotFoundException() {
		super("Competition does not exist.");
	}
	
	public CompetitionNotFoundException(String message) {
		super(message);
	}

	public CompetitionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


}
