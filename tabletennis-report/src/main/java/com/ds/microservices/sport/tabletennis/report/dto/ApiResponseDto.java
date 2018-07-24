package com.ds.microservices.sport.tabletennis.report.dto;

public class ApiResponseDto<T> {
	private T response;

	public ApiResponseDto(T response) {
		super();
		this.response = response;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}


}
