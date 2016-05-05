package com.hallocasa.model.session;

/**
 * Exception for the login process
 * @author David Mantilla
 *
 */
public class LoginFailedException extends Exception {

	/* Static fields */
	private static final long serialVersionUID = -4658091983199893412L;

	/**
	 * Constructor
	 */
	public LoginFailedException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LoginFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LoginFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public LoginFailedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LoginFailedException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getLocalizedMessage() {
		// TODO: i18n
		return getMessage();
	}

}
