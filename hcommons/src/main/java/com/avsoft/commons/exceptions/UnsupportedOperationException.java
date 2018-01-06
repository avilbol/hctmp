package com.avsoft.commons.exceptions;

public class UnsupportedOperationException extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;

	public UnsupportedOperationException() {
	}

	public UnsupportedOperationException(String message) {
		super(message);
	}

	public UnsupportedOperationException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
