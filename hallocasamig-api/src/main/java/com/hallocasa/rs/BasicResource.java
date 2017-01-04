package com.hallocasa.rs;

import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_CLIENT_ID_HEADER;
import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_CODE_HEADER;
import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_TOKEN_HEADER;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BasicResource {

	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{path:.*}")
	public Response preflight() {
		String allowedHeaders = String.format(
    			"origin, content-type, accept, authorization, %s, %s, %s", 
    			O_AUTH_TOKEN_HEADER, O_AUTH_CODE_HEADER, O_AUTH_CLIENT_ID_HEADER);
		return Response.ok()
				.allow("POST", "GET", "PUT", "UPDATE", "OPTIONS", "HEAD")
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", allowedHeaders)
				.build();
	}
}
