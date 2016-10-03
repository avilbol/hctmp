package com.hallocasa.rs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;


public class MainAppServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		try {
			// dynamically recognize an OAuth profile based on request
			// characteristic (params,
			// method, content type etc.), perform validation
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

			// validateRedirectionURI(oauthRequest)

			// build OAuth response
			OAuthResponse resp = OAuthASResponse.authorizationResponse(request, 0).location("hallocasa.com")
					.buildQueryMessage();

			response.sendRedirect(resp.getLocationUri());

			// if something goes wrong
		} catch (OAuthProblemException ex) {

			OAuthResponse resp;
			try {
				resp = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(ex)
						.location(ex.getRedirectUri()).buildQueryMessage();
				response.sendRedirect(resp.getLocationUri());
			} catch (OAuthSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OAuthTokenRequest oauthRequest = null;

		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		try {
			oauthRequest = new OAuthTokenRequest(request);

			validateClient(oauthRequest);

			String authzCode = oauthRequest.getCode();

			// some code
			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();

			// some code
			OAuthResponse r = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).setAccessToken(accessToken)
					.setExpiresIn("3600").setRefreshToken(refreshToken).buildJSONMessage();

			response.setStatus(r.getResponseStatus());

			PrintWriter pw = response.getWriter();
			pw.print(r.getBody());
			pw.flush();
			pw.close();
			// if something goes wrong
		} catch (OAuthProblemException | OAuthSystemException ex) {
			try {
				OAuthResponse r = OAuthResponse.errorResponse(401).error((OAuthProblemException) ex).buildJSONMessage();
				response.setStatus(r.getResponseStatus());
				response.sendError(401);
			} catch (OAuthSystemException ex2) {

			}

		}

	}

	private void validateClient(OAuthTokenRequest myRequest) {
		return;
	}

}
