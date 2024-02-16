package com.asj.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.asj.api.models.common.ErrorResponse;
import com.asj.api.models.common.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidIdentifierException.class)
	public ResponseEntity<ErrorResponse> handleInvalidIdentifierException(InvalidIdentifierException e) {
		ErrorResponse response = new ErrorResponse(400, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ValidationErrorException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationErrorException(ValidationErrorException e) {
		ValidationErrorResponse response = new ValidationErrorResponse(400, e.getMessage(), e.getErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e) {
		ErrorResponse response = new ErrorResponse(401, e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
		ErrorResponse response = new ErrorResponse(404, e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(AssociatedEntitiesException.class)
	public ResponseEntity<ErrorResponse> handleAssociatedEntitiesException(AssociatedEntitiesException e) {
		ErrorResponse response = new ErrorResponse(409, e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(UniqueViolationException.class)
	public ResponseEntity<ErrorResponse> handleUniqueViolationException(UniqueViolationException e) {
		ErrorResponse response = new ErrorResponse(409, e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
		ErrorResponse response = new ErrorResponse(500, "An unexpected error occurred");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
