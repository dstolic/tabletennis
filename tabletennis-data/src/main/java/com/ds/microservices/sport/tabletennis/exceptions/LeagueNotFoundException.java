package com.ds.microservices.sport.tabletennis.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Allow the controller to return a 404 if an item is not found by simply
 * throwing this exception. The @ResponseStatus causes Spring MVC to return a
 * 404 instead of the usual 500.
 * 
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeagueNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LeagueNotFoundException() {
		super("No leagues ");
	}

	public LeagueNotFoundException(String data) {
		super("No such league: " + data);
	}
}
