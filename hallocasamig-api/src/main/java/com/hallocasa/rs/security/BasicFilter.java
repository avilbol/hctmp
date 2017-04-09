package com.hallocasa.rs.security;

import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_CLIENT_ID_HEADER;
import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_CODE_HEADER;
import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_TOKEN_HEADER;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.hallocasa.services.security.AuthorizationCodeService;

@Provider
public class BasicFilter implements ContainerResponseFilter{

	@EJB
	AuthorizationCodeService authorizationCodeService;
	
    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext response) throws IOException {
    	response.getHeaders().add("Access-Control-Allow-Origin", "*");
    	String allowedHeaders = String.format(
    			"origin, content-type, Content-Encoding, accept, authorization, %s, %s, %s", 
    			O_AUTH_TOKEN_HEADER, O_AUTH_CODE_HEADER, O_AUTH_CLIENT_ID_HEADER);
        response.getHeaders().add("Access-Control-Allow-Headers",
                allowedHeaders);
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}