package com.hallocasa.rs.mapper;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hallocasa.utils.constants.exceptions.SecurityException;

public class SecurityExceptionMapper implements ExceptionMapper<SecurityException> {

	/**
	 * Log del mapper
	 */
	private static final Logger LOG = LogManager.getLogger(SecurityExceptionMapper.class);
	
	@Override
	public Response toResponse(SecurityException exception) {
		int httpStatus = translateExceptionCodeToHTTPCode(exception.getStatus());
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(httpStatus);
		errorMessage.setStatus(httpStatus);
		errorMessage.setDeveloperMessage(exception.getMessage());
		errorMessage.setMessage(exception.getMessage());
		Response response = Response.status(httpStatus)
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON).
				build();
		LOG.error(response, exception);
		return response;
	}

	/**
	 * Convierte un codigo de excepcion interno en un
	 * codigo del protocolo http
	 * @param status codigo de excepcion interno
	 * @return codigo del protocolo http
	 */
	private int translateExceptionCodeToHTTPCode(int status) {
		int httpStatus = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		switch (status) {
		case SecurityException.UNEXISTENT_TOKEN:
			httpStatus = Response.Status.UNAUTHORIZED.getStatusCode();
			break;
		case SecurityException.EXPIRED_TOKEN:
			httpStatus = Response.Status.UNAUTHORIZED.getStatusCode();
			break;
		case SecurityException.INVALID_AUTH_CODE:
			httpStatus = Response.Status.UNAUTHORIZED.getStatusCode();
			break;
		case SecurityException.EMAIL_ALREADY_EXISTS:
			httpStatus = Response.Status.CONFLICT.getStatusCode();
			break;
		case SecurityException.INACTIVE_USER:
			httpStatus = Response.Status.FORBIDDEN.getStatusCode();
			break;
		default:
			break;
		}
		
		return httpStatus;
	}
	
}
