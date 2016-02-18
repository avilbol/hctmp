package com.hallocasa.commons.exceptions.services;

/**
 * Exception to be thrown when the token is expired
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ExpiredTokenException extends Exception {

	/* static fields */
	private static final long serialVersionUID = 2146150377923655899L;

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 */
	public ExpiredTokenException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public ExpiredTokenException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public ExpiredTokenException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public ExpiredTokenException(String message, Throwable cause) {
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
	public ExpiredTokenException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/* Methods */

	/* Getters & Setters */
}
