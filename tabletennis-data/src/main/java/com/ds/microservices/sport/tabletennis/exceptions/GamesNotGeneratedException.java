package com.ds.microservices.sport.tabletennis.exceptions;

public class GamesNotGeneratedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GamesNotGeneratedException() {
		super();
	}

	public GamesNotGeneratedException(String message) {
		super(message);
	}

	public GamesNotGeneratedException(String message, Throwable cause) {
		super(message, cause);
	}


}
