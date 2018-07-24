package com.ds.microservices.sport.tabletennis.report.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ds.microservices.sport.tabletennis.report.dto.ApiResponseDto;
import com.ds.microservices.sport.tabletennis.report.dto.ErrorDto;
import com.ds.microservices.sport.tabletennis.report.exceptions.CompetitionNotFoundException;
import com.ds.microservices.sport.tabletennis.report.exceptions.GameNotFoundException;
import com.ds.microservices.sport.tabletennis.report.exceptions.PlayerNotFoundException;

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
