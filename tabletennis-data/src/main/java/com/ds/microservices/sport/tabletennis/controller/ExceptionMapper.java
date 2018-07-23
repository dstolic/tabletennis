package com.ds.microservices.sport.tabletennis.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ds.microservices.sport.tabletennis.dto.ApiResponseDto;
import com.ds.microservices.sport.tabletennis.dto.ErrorDto;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionAlreadyCompletedException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotCompletedException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotCorrectNumberOfPlayersException;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotFoundException;
import com.ds.microservices.sport.tabletennis.exceptions.GameNotFoundException;
import com.ds.microservices.sport.tabletennis.exceptions.PlayerNotFoundException;

@ControllerAdvice
@Component
public class ExceptionMapper extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(CompetitionNotFoundException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handleCompetitionNotFoundException() {
		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto(messageSource.getMessage("error.competition.notfound", null, Locale.getDefault()), HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CompetitionNotCompletedException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handleCompetitionNotCompletedException() {
		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto(messageSource.getMessage("error.competition.notcompleted", null, Locale.getDefault()), HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CompetitionAlreadyCompletedException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handleCompetitionAlreadyCompletedException() {
		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto(messageSource.getMessage("error.competition.completed", null, Locale.getDefault()), HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CompetitionNotCorrectNumberOfPlayersException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handleCompetitionNotCorrectNumberOfPlayersException() {
		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto(messageSource.getMessage("error.competition.notcorrect.players", null, Locale.getDefault()), HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handlePlayerNotFoundExceptionException() {
		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto(messageSource.getMessage("error.player.notfound", null, Locale.getDefault()), HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(GameNotFoundException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handleGameNotFoundException() {
		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto(messageSource.getMessage("error.game.notfound", null, Locale.getDefault()), HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
	}
}
