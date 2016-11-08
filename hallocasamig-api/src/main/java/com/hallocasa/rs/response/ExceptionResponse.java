package com.hallocasa.rs.response;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hallocasa.utils.constants.exceptions.FatalException;

public class ExceptionResponse {

	private Integer status;

	private Exception exc;

	private String msg;

	public static String error(Exception e) throws JsonGenerationException, JsonMappingException, IOException {
		ExceptionResponse response = new ExceptionResponse();
		response.setMsg("An internal error has ocurred");
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setExc(new FatalException(e.getMessage(), e));
		return new ObjectMapper().writeValueAsString(response);
	}
	
	public static String unauthorized(Exception e) throws JsonGenerationException, JsonMappingException, IOException {
		ExceptionResponse response = new ExceptionResponse();
		response.setMsg("An internal error has ocurred");
		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.setExc(new FatalException(e.getMessage(), e));
		return new ObjectMapper().writeValueAsString(response);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Exception getExc() {
		return exc;
	}

	public void setExc(Exception exc) {
		this.exc = exc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
