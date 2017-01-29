package com.hallocasa.utils.constants.exceptions;

import javax.ejb.ApplicationException;

/**
 * Excepcion a usar cuando se intenta realizar la autenticacion con la aplicacion
 * @author avillamil
 */
@ApplicationException
public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;

	public static final int UNEXISTENT_TOKEN = -1;
	public static final int EXPIRED_TOKEN = -2;
	public static final int INVALID_AUTH_CODE = -3;
	public static final int EMAIL_ALREADY_EXISTS = -4;
	public static final int FORBIDDEN = -5;
	
	private int status;
	
	public SecurityException() {
	}

	public SecurityException(String message) {
		super(message);
	}
	
	public SecurityException(String message, int status) {
		super(message);
		this.setStatus(status);

	}

	public SecurityException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
