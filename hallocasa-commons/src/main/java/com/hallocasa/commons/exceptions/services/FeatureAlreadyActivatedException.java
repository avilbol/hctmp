/**
 * 
 */
package com.hallocasa.commons.exceptions.services;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
public class FeatureAlreadyActivatedException extends Exception {

	private static final long serialVersionUID = 2860328714125234567L;

	/**
	 * Constructor
	 */
	public FeatureAlreadyActivatedException() {
	}

	/**
	 * Constructor
	 * @param message
	 */
	public FeatureAlreadyActivatedException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public FeatureAlreadyActivatedException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public FeatureAlreadyActivatedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FeatureAlreadyActivatedException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}