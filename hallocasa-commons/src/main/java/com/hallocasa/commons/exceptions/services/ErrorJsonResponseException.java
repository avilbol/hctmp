package com.hallocasa.commons.exceptions.services;

/**
 * Exception to throw when api returns an error in json
 * @author Alexander Villamil
 *
 */
public class ErrorJsonResponseException extends Exception {

	private static final long serialVersionUID = 2860328714125234567L;

	/**
	 * Constructor
	 */
	public ErrorJsonResponseException() {
	}

	/**
	 * Constructor
	 * @param message
	 */
	public ErrorJsonResponseException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public ErrorJsonResponseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public ErrorJsonResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ErrorJsonResponseException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
