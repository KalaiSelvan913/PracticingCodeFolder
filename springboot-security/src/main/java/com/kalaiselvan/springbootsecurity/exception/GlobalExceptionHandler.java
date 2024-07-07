package com.kalaiselvan.springbootsecurity.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException ex,
			WebRequest request) {
		logger.error("Department not found: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
		logger.error("Employee not found: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DepartmentAlreadyExistsException.class)
	public ResponseEntity<String> handleDepartmentAlreadyExistsException(DepartmentAlreadyExistsException ex,
			WebRequest request) {
		logger.error("Department already exists: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String message = "Data integrity violation: " + ex.getMostSpecificCause().getMessage();
		logger.error("Data integrity violation: {}", message, ex);
		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String message = "Invalid argument type: " + ex.getName();
		logger.error("Invalid argument type: {}", message, ex);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		logger.error("Method argument not valid: {}", errors, ex);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException ex,
			WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations().forEach(violation -> {
			String fieldName = violation.getPropertyPath().toString();
			String errorMessage = violation.getMessage();
			errors.put(fieldName, errorMessage);
		});
		logger.error("Constraint violation: {}", errors, ex);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRunTimeException(RuntimeException ex, WebRequest request) {
		logger.error("Runtime exception: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
		logger.error("Unhandled exception: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
