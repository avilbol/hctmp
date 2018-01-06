package com.hallocasa.rs.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hallocasa.utils.constants.exceptions.ClientFocusedException;

public class ClientFocusedExceptionMapper implements ExceptionMapper<ClientFocusedException> {

	/**
	 * Log del mapper
	 */
	private static final Logger LOG = LogManager.getLogger(SecurityExceptionMapper.class);
	
	@Override
	public Response toResponse(ClientFocusedException exception) {
		int httpStatus = exception.getHttpCode();
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(httpStatus);
		errorMessage.setStatus(httpStatus);
		errorMessage.setLangMessage(exception.getLang());
		errorMessage.setDeveloperMessage(exception.getMessage());
		errorMessage.setMessage(exception.getMessage());
		Response response = Response.status(httpStatus)
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON).
				build();
		LOG.error(response, exception);
		return response;
	}
	
}
