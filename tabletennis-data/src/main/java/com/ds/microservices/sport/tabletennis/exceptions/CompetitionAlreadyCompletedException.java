package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionAlreadyCompletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionAlreadyCompletedException() {
		super("Previous competition is not completed. Unfinished games exists.");
	}

}
