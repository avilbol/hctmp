package com.mobiera.hallocasa.commons.exceptions.services;

/**
 * Exception which is thrown every time a partnership that already exists is
 * been created
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class PartnershipAlreadyExistsException extends Exception {

	/* static fields */

	/* instance variables */
	private static final long serialVersionUID = -4682489800281484796L;

	/* constructors */

	/**
	 * Constructor
	 */
	public PartnershipAlreadyExistsException() {
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
	public PartnershipAlreadyExistsException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public PartnershipAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public PartnershipAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public PartnershipAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	/* Methods */

	/* Getters & Setters */

}
