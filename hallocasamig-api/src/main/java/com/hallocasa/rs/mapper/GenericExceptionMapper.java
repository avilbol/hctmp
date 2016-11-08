package com.hallocasa.rs.mapper;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Convierte excepciones Java en mensajes de error Rest
 * @author amacoder
 * {@link https://github.com/Codingpedia/demo-rest-jersey-spring}
 * <p><b>Fecha:</b>06/06/2014</p>
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
 
	/**
	 * Log del mapper
	 */
	private static final Logger LOG = LogManager.getLogger(GenericExceptionMapper.class);
	
	/**
	 * Estado temporalmente redirigido
	 */
	public static final int TEMPORARY_REDIRECT = 307;
	
	/**
	 * Tiempo de esapera gotado
	 */
	public static final int TIMED_OUT = 408;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response toResponse(Throwable ex) {
		
		ErrorMessage errorMessage = new ErrorMessage();		
		setHttpStatus(ex, errorMessage);
		errorMessage.setCode(AppConstants.GENERIC_APP_ERROR_CODE);
		errorMessage.setMessage(ex.getMessage());
		StringWriter errorStackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(errorStackTrace));
		errorMessage.setDeveloperMessage(errorStackTrace.toString());
		errorMessage.setLink(AppConstants.BLOG_POST_URL);
				
		LOG.error(errorMessage, ex);
		return Response.status(errorMessage.getStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();	
	}

	/**
	 * Obtiene la equivalencia de HTTP para el tipo de excepcion
	 * dada
	 * @param ex excepcion a traducir
	 * @param errorMessage mensaje de error
	 */
	private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
		if (ex instanceof WebApplicationException) { //NICE way to combine both of methods, say it in the blog 
			errorMessage.setStatus(((WebApplicationException) ex).getResponse().getStatus());
		} else {
			errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR
					.getStatusCode()); // defaults to internal server error 500
		}
	}
}
