package com.mobiera.hallocasa.commons.exceptions.services;

/**
 * Exception for login process in case of invalid email
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class InvalidIpLoginException extends ServiceException {

	/* static fields */
	private static final long serialVersionUID = 8883371648569100306L;

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 */
	public InvalidIpLoginException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidIpLoginException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public InvalidIpLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public InvalidIpLoginException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public InvalidIpLoginException(Throwable cause) {
		super(cause);
	}

	/* Methods */

	/* Getters & Setters */
}
