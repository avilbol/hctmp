package com.hallocasa.rs.security;

import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_TOKEN_HEADER;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.hallocasa.services.security.SecurityTokenService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@EJB
	SecurityTokenService securityTokenService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		securityTokenService.validate(requestContext.getHeaders().getFirst(O_AUTH_TOKEN_HEADER));
	}
}