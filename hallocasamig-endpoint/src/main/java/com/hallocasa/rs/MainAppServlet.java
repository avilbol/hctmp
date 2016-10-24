package com.hallocasa.rs;

import java.io.IOException;
import java.util.Optional;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hallocasa.services.security.AuthorizationCodeService;
import com.hallocasa.vo.security.AuthorizationCode;

public class MainAppServlet extends HttpServlet {

	private static final long serialVersionUID = 5923776976716462854L;

	@EJB
	AuthorizationCodeService authorizationCodeService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authorizeAppCode(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authorizeAppCode(request, response);
	}

	private void authorizeAppCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Optional<AuthorizationCode> authCode = authorizationCodeService.find(request.getHeader("O-Auth-ClientId"),
				request.getHeader("O-Auth-Code"));
		if (!authCode.isPresent()) {
			response.sendRedirect("localhost:8080/hallocasa-api/security/auth?redirect-uri=" + request.getRequestURI());
		}
	}
}
