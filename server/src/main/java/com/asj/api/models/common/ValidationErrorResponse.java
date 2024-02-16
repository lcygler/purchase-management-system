package com.asj.api.models.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ValidationErrorResponse {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private String timestamp;
	private int status;
	private String error;
	private String message;
	private Map<String, String> fieldErrors;

	public ValidationErrorResponse(int status, String message, Map<String, String> fieldErrors) {
		this.timestamp = LocalDateTime.now().format(FORMATTER);
		this.status = status;
		this.error = HttpStatus.valueOf(status).getReasonPhrase();
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	@Override
	public String toString() {
		return "ValidationErrorResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error
				+ ", message=" + message + ", fieldErrors=" + fieldErrors + "]";
	}

}
