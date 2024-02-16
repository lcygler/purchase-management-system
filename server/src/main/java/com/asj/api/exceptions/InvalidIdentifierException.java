package com.asj.api.exceptions;

public class InvalidIdentifierException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidIdentifierException(String message) {
		super(message);
	}
}
