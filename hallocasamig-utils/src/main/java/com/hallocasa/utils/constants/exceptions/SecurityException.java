package com.hallocasa.utils.constants.exceptions;

public class SecurityException extends Exception {

	private static final long serialVersionUID = -8829946088535207080L;

	public SecurityException() {
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
