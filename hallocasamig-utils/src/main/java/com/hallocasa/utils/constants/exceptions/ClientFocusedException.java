package com.hallocasa.utils.constants.exceptions;

import org.apache.http.HttpStatus;

/**
 * Exception generated in backend when a custom message to be deployed in client
 * consumer (front end is an example)
 * @author avillamil
 */
public class ClientFocusedException  extends RuntimeException {

	private static final long serialVersionUID = -8829946088535207080L;
	
	private String lang;
	private Integer httpCode;

	public ClientFocusedException() {
	}

	private ClientFocusedException(String message, Integer httpCode, String lang) {
		super(message);
		this.lang = lang;
		this.httpCode = httpCode;
	}
	
	public static void throwBadRequest(String message, String lang){
		throw new ClientFocusedException(message, HttpStatus.SC_BAD_REQUEST, lang);
	}
	
	public static void throwConflict(String message, String lang){
		throw new ClientFocusedException(message, HttpStatus.SC_CONFLICT, lang);
	}

	public String getLang() {
		return lang;
	}

	public Integer getHttpCode() {
		return httpCode;
	}
}
