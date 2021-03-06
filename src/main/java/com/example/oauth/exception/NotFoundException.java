package com.example.oauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2283387356748036081L;
	
	public NotFoundException(String message) {
		super(message, null, false, false);
	}

}
