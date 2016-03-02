/**
 * 
 */
package com.hallocasa.commons.exceptions.services;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
public class AppAlreadyActivatedException extends Exception {
	
	/* static fields */
	private static final long serialVersionUID = -1080799793620442324L;

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 */
	public AppAlreadyActivatedException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public AppAlreadyActivatedException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public AppAlreadyActivatedException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public AppAlreadyActivatedException(String message, Throwable cause) {
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
	public AppAlreadyActivatedException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
