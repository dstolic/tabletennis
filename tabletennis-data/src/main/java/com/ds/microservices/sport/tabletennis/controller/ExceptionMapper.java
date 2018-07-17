package com.ds.microservices.sport.tabletennis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ds.microservices.sport.tabletennis.dto.ApiResponseDto;
import com.ds.microservices.sport.tabletennis.dto.ErrorDto;
import com.ds.microservices.sport.tabletennis.exceptions.CompetitionNotFoundException;

@ControllerAdvice
@Component
public class ExceptionMapper extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CompetitionNotFoundException.class)
	public ResponseEntity<ApiResponseDto<ErrorDto>> handleCompetitionNotFoundException() {

		return new ResponseEntity<>(
                new ApiResponseDto<>(new ErrorDto("Competition not found.", HttpStatus.BAD_REQUEST.value())),
                HttpStatus.BAD_REQUEST);
		
	}

}
