package com.asj.api.exceptions;

public class UniqueViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UniqueViolationException(String message) {
		super(message);
	}
}
