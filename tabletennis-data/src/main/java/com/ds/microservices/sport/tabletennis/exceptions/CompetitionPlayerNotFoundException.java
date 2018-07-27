package com.ds.microservices.sport.tabletennis.exceptions;

public class CompetitionPlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionPlayerNotFoundException() {
		super();
	}

	public CompetitionPlayerNotFoundException(String message) {
		super(message);
	}

	public CompetitionPlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


}
