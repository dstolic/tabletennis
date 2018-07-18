package com.ds.microservices.sport.tabletennis.controller;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

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

@ControllerAdvice
@Component
public class ExceptionMapper extends ResponseEntityExceptionHandler {
	
	protected Logger logger = Logger.getLogger(ExceptionMapper.class.getName());
	
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

}
