package com.hallocasa.rs.mapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Mensaje de error generico.
 * @author amacoder.
 * {@link https://github.com/Codingpedia/demo-rest-jersey-spring}.
 * <p><b>Fecha:</b>06/06/2014</p>
 */
@XmlRootElement
public class ErrorMessage {
	
	/** 
	 * contains the same HTTP Status code returned by the server.
	 */
	@XmlElement(name = "status")
	private int status;
	
	/** 
	 * application specific error code.
	 */
	@XmlElement(name = "code")
	private int code;
	
	/**
	 *  message describing the error.
	 */
	@XmlElement(name = "message")
	private String message;
		
	/** 
	 * link point to page where the error message is documented.
	 */
	@XmlElement(name = "link")
	private String link;
	
	/** 
	 * extra information that might useful for developers. 
	 */
	@XmlElement(name = "developerMessage")
	private String developerMessage;	

	/**
	 * Obtiene el estado de error.
	 * @return codigo del estado de error.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Establece el estado de error.
	 * @param status estado de error.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Obtiene el codigo del error.
	 * @return codigo de error.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Establece el codigo de error.
	 * @param code codigo de error.
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * Obtiene el mensaje de error.
	 * @return mensaje de error.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Establece el mensaje de error.
	 * @param message mensaje de error.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Obtiene el mensaje de error orientado al desarrollador.
	 * @return mensaje de error tecnico.
	 */
	public String getDeveloperMessage() {
		return developerMessage;
	}

	/**
	 * Establece un mensaje de error tecnico que debe ser.
	 * revisado por el desarrollador.
	 * @param developerMessage mensaje de error tecnico.
	 */
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	/**
	 * Obtiene un enlace que contiene la representacion del.
	 * error
	 * @return enlace error.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Establece el enlace que contiene la representacion.
	 * del error
	 * @param link enlace del error.
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	
	/**
	 * Crea el mensaje de error envolviendo la excepcion dada.
	 * @param ex excepcion que encapsula.
	 */
	public ErrorMessage(NotFoundException ex) {
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.message = ex.getMessage();
		this.link = "https://jersey.java.net/apidocs/2.8/jersey/javax/ws/rs/NotFoundException.html";		
	}

	/**
	 * Constructor por defecto.
	 */
	public ErrorMessage() { }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ErrorMessage [status=" + status + ", code=" + code
				+ ", message=" + message + ", link=" + link
				+ ", developerMessage=" + developerMessage + "]";
	}
}