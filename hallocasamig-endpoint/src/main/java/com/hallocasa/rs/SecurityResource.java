package com.hallocasa.rs;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.as.response.OAuthASResponse.OAuthAuthorizationResponseBuilder;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.OAuthResponse.OAuthResponseBuilder;

@Path("/auth")
public class SecurityResource {

	@GET
	@Path("/auth")
	public Response authEndpoint(@Context HttpServletRequest request) throws URISyntaxException {
		try {
			// dynamically recognize an OAuth profile based on request
			// characteristic (params,
			// method, content type etc.), perform validation
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
			OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

			validateRedirectionURI(oauthRequest);

			// build OAuth response
			OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
					HttpServletResponse.SC_FOUND);

			String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
			final OAuthResponse response = builder
					.setCode(oauthIssuerImpl.authorizationCode())
					.location(redirectURI)
					.buildQueryMessage();
			URI url = new URI(response.getLocationUri());
			return Response.status(response.getResponseStatus()).location(url).build();
		} catch (OAuthProblemException ex) {

			OAuthResponse resp;
			try {
				resp = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(ex)
						.location(ex.getRedirectUri()).buildQueryMessage();
				Response response = Response.status(resp.getResponseStatus()).header("Location", resp.getLocationUri())
						.build();
				return response;
			} catch (OAuthSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void validateRedirectionURI(OAuthAuthzRequest oauthRequest) {
		// TODO Auto-generated method stub

	}

}
