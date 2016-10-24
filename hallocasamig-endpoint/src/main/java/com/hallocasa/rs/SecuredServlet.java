package com.hallocasa.rs;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hallocasa.services.security.SecurityTokenService;
import com.hallocasa.utils.constants.exceptions.SecurityException;

public class SecuredServlet extends HttpServlet {

	private static final long serialVersionUID = 5923776976716462854L;
	
	@EJB
	SecurityTokenService securityTokenService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authorizeAppToken(request.getHeader("O-Auth-Token"), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authorizeAppToken(request.getHeader("O-Auth-Token"), request, response);
	}
	
	private void authorizeAppToken(String oAuthToken, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		try {
			securityTokenService.validate(oAuthToken);
		} catch (SecurityException e) {
			response.sendRedirect("/login?redirect-uri=" + request.getRequestURL());
		}
	}
}
