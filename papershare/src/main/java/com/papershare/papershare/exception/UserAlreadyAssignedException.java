package com.papershare.papershare.exception;

public class UserAlreadyAssignedException extends RuntimeException{
	public UserAlreadyAssignedException(String message) {
		super(message);
	}
}
