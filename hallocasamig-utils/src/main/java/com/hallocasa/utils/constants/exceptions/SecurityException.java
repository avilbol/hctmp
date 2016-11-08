package com.hallocasa.utils.constants.exceptions;

import javax.ejb.ApplicationException;

/**
 * Excepcion a usar cuando se intenta realizar la autenticacion con la aplicacion
 * @author avillamil
 */
@ApplicationException
public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;

	/**
	 * C&oacute;digo de error para denotar que el token no se encuentra registrado
	 */
	public static final int UNEXISTENT_TOKEN = -1;
	
	/**
	 * C&oacute;digo de error para denotar que el token se encuentra vencido.
	 */
	public static final int EXPIRED_TOKEN = -2;
	
	/**
	 * C&oacute;digo de error para denotar que el codigo de autorizacion es invalido.
	 */
	public static final int INVALID_AUTH_CODE = -3;
	
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
