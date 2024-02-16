package com.asj.api.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Associated entities found";

	public InvalidCredentialsException() {
		super(DEFAULT_MESSAGE);
	}

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
