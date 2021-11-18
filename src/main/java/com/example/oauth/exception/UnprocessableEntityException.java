package com.example.oauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
	
	private static final long serialVersionUID = 5459059412141440699L;
	
	public UnprocessableEntityException(String message) {
		super(message, null, false, false);
	}
	
}
