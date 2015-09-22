package com.hallocasa.model.controlaccess;

/**
 * Exception when a forbidden page or forbidden state is required
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 231764646185186265L;

	/**
	 * Constructor
	 */
	public ForbiddenException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public ForbiddenException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public ForbiddenException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public ForbiddenException(String message, Throwable cause) {
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
	public ForbiddenException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
