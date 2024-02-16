package com.asj.api.exceptions;

public class AssociatedEntitiesException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Associated entities found";

	public AssociatedEntitiesException() {
		super(DEFAULT_MESSAGE);
	}

	public AssociatedEntitiesException(String message) {
		super(message);
	}
}
