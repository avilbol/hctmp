package com.hallocasa.rs;

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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.as.response.OAuthASResponse.OAuthAuthorizationResponseBuilder;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

import com.hallocasa.services.security.AuthorizationCodeService;
import com.hallocasa.services.security.SecurityTokenService;
import com.hallocasa.vo.security.AuthorizationCode;
import com.hallocasa.vo.security.SecurityToken;

import io.swagger.annotations.OAuth2Definition;

@Path("/security")
public class SecurityResource {

	@EJB
	AuthorizationCodeService authCodeService;

	@EJB
	SecurityTokenService securityTokenService;

	@GET
	@Path("/auth")
	@Produces("application/json")
	public Response authorize(@Context HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
		OAuthAuthzRequest oauthRequest = null;
		try {
			oauthRequest = new OAuthAuthzRequest(request);
			String clientId = oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID);
			AuthorizationCode authCode = authCodeService.generate(clientId);
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
					HttpServletResponse.SC_FOUND);
			builder.setCode(authCode.getAuthCode());
			String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
			final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
			URI url = new URI(response.getLocationUri());
			return Response.status(response.getResponseStatus()).location(url).build();
		} catch (OAuthProblemException e) {
			final Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_FOUND);
			String redirectUri = e.getRedirectUri();
			if (OAuthUtils.isEmpty(redirectUri)) {
				throw new WebApplicationException(
						responseBuilder.entity("OAuth callback url needs to be provided by client!!!").build());
			}
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
	public Response loadToken(@Context HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
		OAuthTokenRequest oauthRequest = null;
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		oauthIssuerImpl.accessToken();
		try {
			oauthRequest = new OAuthTokenRequest(request);
			String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
			String clientId = oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID);
			// check if client id and authorization code is valid
			if (!authCodeService.find(clientId, authCode).isPresent()) {
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
						.setErrorDescription("client_id or authorization code not found").buildJSONMessage();
				return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
			}
			// check username and password
			// TODO : create integration with service that validates user and password
			if (!"admin".equals(oauthRequest.getPassword()) || !"admin".equals(oauthRequest.getUsername())) {
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_GRANT)
						.setErrorDescription("invalid username or password").buildJSONMessage();
				return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
			}
			SecurityToken token = securityTokenService.generate();
			OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(token.getTokenValue()).setExpiresIn(String.valueOf(token.getExpiresIn()))
					.buildJSONMessage();
			return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
		} catch (OAuthProblemException e) {
			OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
					.buildJSONMessage();
			return Response.status(res.getResponseStatus()).entity(res.getBody()).build();
		}

	}

}