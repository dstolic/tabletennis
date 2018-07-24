package com.ds.microservices.sport.tabletennis.report.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException() {
		super();
	}

	public PlayerNotFoundException(String message) {
		super(message);
	}

	public PlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


}
