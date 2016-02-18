/**
 * 
 */
package com.hallocasa.commons.exceptions.services;

/**
 * Invalid token exception
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = 2829527129754852918L;

	/**
	 * Constructor
	 */
	public InvalidTokenException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public InvalidTokenException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public InvalidTokenException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidTokenException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
