package gr.filatok.library_api.api;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gr.filatok.library_api.business.dtos.exceptions.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound(EntityNotFoundException ex)
	{
		String msg = String.format("Entity Not Found :: %s", ex.getMessage());
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex)
	{
		String msg = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage())
				.collect(Collectors.joining(", "));
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex)
	{
		String msg = String.format("Constraint Violation :: %s", ex.getMessage());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneral(Exception ex)
	{
		String msg = String.format("Internal Server Error :: %s", ex.getMessage());
		return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
