package com.ds.microservices.sport.tabletennis.exceptions;


public class CompetitionNotCompletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionNotCompletedException() {
		super("Previous competition is not completed. Unfinished games exists.");
	}

}
