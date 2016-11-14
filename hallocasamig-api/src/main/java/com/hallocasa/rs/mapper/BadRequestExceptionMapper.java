package com.hallocasa.rs.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hallocasa.utils.constants.exceptions.BadRequestException;

public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	/**
	 * Log del mapper
	 */
	private static final Logger LOG = LogManager.getLogger(SecurityExceptionMapper.class);
	
	@Override
	public Response toResponse(BadRequestException exception) {
		int httpStatus = HttpStatus.SC_BAD_REQUEST;
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
	
}
