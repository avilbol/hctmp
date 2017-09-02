package com.avsoft.commons.exceptions;

/**
 * Exception to handle unexpected errors
 * @author avillamil
 */
public class FatalException extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;

	public FatalException() {
	}

	public FatalException(String message) {
		super(message);
	}

	public FatalException(String message, Throwable throwable) {
		super(message, throwable);
	}

}