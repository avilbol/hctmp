package com.hallocasa.rs.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hallocasa.utils.constants.exceptions.ServiceException;

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	/**
	 * Logger del mapper
	 */
	private static final Logger LOG = LogManager.getLogger(ServiceExceptionMapper.class);
	
	@Override
	public Response toResponse(ServiceException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(ex.getHttpStatus());
		errorMessage.setStatus(ex.getHttpStatus());
		errorMessage.setDeveloperMessage(ex.getMessage());
		errorMessage.setMessage(ex.getMessage());
		Response response = Response.status(ex.getHttpStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON).
				build();
		LOG.error(response, ex);
		return response;
	}

}
