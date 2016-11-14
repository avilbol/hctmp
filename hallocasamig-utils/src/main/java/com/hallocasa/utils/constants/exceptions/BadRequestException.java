package com.hallocasa.utils.constants.exceptions;

/**
 * Exception to throw when bad request
 * @author avillamil
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;

	public BadRequestException() {
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
