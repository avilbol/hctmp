package com.hallocasa.utils.constants.exceptions;

public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;

	/**
	 * C&oacute;digo de error para denotar que la informaci&oacute;n buscada no fue encontrada.
	 */
	public static final int UNEXISTENT_TOKEN = -1;
	
	/**
	 * C&oacute;digo de error para denotar que la informaci&oacute;n buscada no est&aacute; disponible.
	 */
	public static final int EXPIRED_TOKEN = -2;
	
	private int status;
	
	public SecurityException() {
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public int getStatus() {
		return status;
	}
}
