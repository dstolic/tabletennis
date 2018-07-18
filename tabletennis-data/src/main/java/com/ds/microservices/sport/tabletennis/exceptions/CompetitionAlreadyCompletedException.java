package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionAlreadyCompletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionAlreadyCompletedException() {
		super("Competition is completed.");
	}

}
