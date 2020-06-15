package com.papershare.papershare.exception;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.exist.http.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.papershare.papershare.DTO.ErrorDetailsDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { UsernameNotFoundException.class, NotFoundException.class })
	public ResponseEntity<ErrorDetailsDTO> handleNotFoundExceptions(Exception exception) {
		return genericHandler(exception, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = { ConstraintViolationException.class, UserAlreadyAssignedException.class, PaperAlreadyExistException.class })
	public ResponseEntity<ErrorDetailsDTO> handleConflictExceptions(Exception exception) {
		return genericHandler(exception, HttpStatus.CONFLICT);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorDetailsDTO> handleExceptions(Exception exception) {
		return genericHandler(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorDetailsDTO> handleConstraintExceptions(MethodArgumentNotValidException exception) {
		exception.printStackTrace();
		return new ResponseEntity<ErrorDetailsDTO>(
				new ErrorDetailsDTO(exception.getMessage(), LocalDate.now(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
	}

	private ResponseEntity<ErrorDetailsDTO> genericHandler(Exception exception, HttpStatus httpStatus) {
		exception.printStackTrace();
		return new ResponseEntity<ErrorDetailsDTO>(
				new ErrorDetailsDTO(exception.getMessage(), LocalDate.now(), httpStatus), httpStatus);

	}
}
