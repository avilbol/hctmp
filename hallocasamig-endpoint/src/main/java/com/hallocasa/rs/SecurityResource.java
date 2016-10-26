package com.hallocasa.rs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.hallocasa.rs.response.ExceptionResponse;
import com.hallocasa.services.security.AuthenticationService;
import com.hallocasa.services.security.AuthorizationCodeService;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.exceptions.InvalidEmailException;
import com.hallocasa.utils.constants.exceptions.InvalidPasswordLoginException;
import com.hallocasa.vo.security.AuthInfo;
import com.hallocasa.vo.security.AuthorizationCode;
import com.hallocasa.vo.security.UserCredentials;

@Path("/security")
public class SecurityResource {

	@EJB
	AuthorizationCodeService authCodeService;

	@EJB
	AuthenticationService authenticationService;

	@GET
	@Path("/auth")
	@Produces("application/json")
	public Response authorize(@Context HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
		OAuthAuthzRequest oauthRequest = null;
		try {
			oauthRequest = new OAuthAuthzRequest(request);
			String clientId = oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID);
			String redirectUri = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
			if (OAuthUtils.isEmpty(redirectUri)) {
				throw new FatalException("OAuth callback url needs to be provided by client!!!");
			}
			AuthorizationCode authCode = authCodeService.generate(clientId);
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
					HttpServletResponse.SC_FOUND);
			builder.setCode(authCode.getAuthCode());
			final OAuthResponse response = builder.location(redirectUri).buildQueryMessage();
			URI url = new URI(response.getLocationUri());
			return Response.status(response.getResponseStatus()).location(url).build();
		} catch (OAuthProblemException e) {
			final Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_FOUND);
			String redirectUri = e.getRedirectUri();
			final OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(e)
					.location(redirectUri).buildQueryMessage();
			final URI location = new URI(response.getLocationUri());
			return responseBuilder.location(location).build();
		}
	}

	@POST
	@Path("/token")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response loadToken(@Context HttpServletRequest request) throws URISyntaxException, OAuthSystemException,
			JsonGenerationException, JsonMappingException, IOException {
		OAuthTokenRequest oauthRequest = null;
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		oauthIssuerImpl.accessToken();
		try {
			oauthRequest = new OAuthTokenRequest(request);
			String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
			String clientId = oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID);
			if(authCode == null){
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
						.setErrorDescription("authorization code not supplied").buildJSONMessage();
				return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
			}
			// check if client id and authorization code is valid
			if (!authCodeService.find(clientId, authCode).isPresent()) {
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
						.setErrorDescription("client_id or authorization code not found").buildJSONMessage();
				return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
			}
			// validate username and password
			String email = oauthRequest.getUsername();
			String password = oauthRequest.getPassword();
			UserCredentials credentials = new UserCredentials(email, password);
			AuthInfo authInfo = authenticationService.authenticate(credentials);
			return Response.status(HttpStatus.SC_ACCEPTED).entity(authInfo).build();
		} catch (OAuthProblemException e) {
			OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
					.buildJSONMessage();
			return Response.status(res.getResponseStatus()).entity(res.getBody()).build();
		} catch (InvalidEmailException e) {
			return Response.status(HttpStatus.SC_UNAUTHORIZED)
					.entity(ExceptionResponse.error(e)).build();
		} catch (InvalidPasswordLoginException e) {
			return Response.status(HttpStatus.SC_UNAUTHORIZED).entity(e).build();
		}
	}
}
