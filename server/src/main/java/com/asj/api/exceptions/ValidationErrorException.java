package com.asj.api.exceptions;

import java.util.Map;

public class ValidationErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Map<String, String> errors;

	public ValidationErrorException(Map<String, String> errors) {
		super("Validation error");
		this.errors = errors;
	}

	public ValidationErrorException(String message, Map<String, String> errors) {
		super(message);
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
