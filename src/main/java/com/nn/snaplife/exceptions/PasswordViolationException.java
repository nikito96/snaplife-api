package com.nn.snaplife.exceptions;

public class PasswordViolationException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordViolationException(String message) {
		super(message);
	}
}
