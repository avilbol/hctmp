package com.hallocasa.rs.security;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.hallocasa.services.security.SecurityTokenService;

@Secured
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@EJB
	SecurityTokenService securityTokenService;
	
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	securityTokenService.validate(requestContext.getHeaders().getFirst("O-Auth-Token"));
    }
}