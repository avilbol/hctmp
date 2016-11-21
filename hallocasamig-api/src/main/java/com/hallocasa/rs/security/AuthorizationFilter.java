package com.hallocasa.rs.security;

import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_CLIENT_ID_HEADER;
import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_CODE_HEADER;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.hallocasa.services.security.AuthorizationCodeService;
import com.hallocasa.utils.constants.errormessages.AuthorizationCodeError;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.vo.security.AuthorizationCode;

@Auth
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter{

	@EJB
	AuthorizationCodeService authorizationCodeService;
	
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	MultivaluedMap<String, String> headers = requestContext.getHeaders();
    	String clientId = headers.getFirst(O_AUTH_CLIENT_ID_HEADER);
    	String code = headers.getFirst(O_AUTH_CODE_HEADER);
    	if(clientId == null || code == null){
    		throw new SecurityException("client_id or authorization code not specified");
    	}
    	Optional<AuthorizationCode> authCode = authorizationCodeService.find(clientId, code);
    	if(!authCode.isPresent()){
    		throw new SecurityException(AuthorizationCodeError.INVALID_AUTH_CODE, 
    				SecurityException.INVALID_AUTH_CODE);
    	}
    }
}