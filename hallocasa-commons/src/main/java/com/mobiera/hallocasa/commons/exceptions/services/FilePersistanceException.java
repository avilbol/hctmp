package com.mobiera.hallocasa.commons.exceptions.services;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class FilePersistanceException extends RuntimeException {

	private static final long serialVersionUID = 1743393978906205547L;

	/**
	 * Constructor
	 */
	public FilePersistanceException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public FilePersistanceException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public FilePersistanceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public FilePersistanceException(String message, Throwable cause) {
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
	public FilePersistanceException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
