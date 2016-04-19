/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.security;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.users.UserUtils;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.LoginFailedException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;

/**
 * 
 * @author david
 */
@ManagedBean(name = "loginDialog")
@ViewScoped
public class LoginDialog {

	/* class variables */
	private static final Logger LOG = Logger.getLogger(LoginDialog.class
			.getName());

	/* dependencies */
	@Inject
	private WebSession webSession;
	@Inject
	private ViewContext viewContext;
	@Inject
	private NavigationHandler navigationHandler;
	
	private String postScript;

	/* instance variables */
	private CredentialVO credentials;

	/**
	 * Initialize
	 */
	@PostConstruct
	public void initialize() {
		credentials = new CredentialVO();
	}

	/**
	 * Process click on the login
	 */
	public void processSubmitClick() {
		try {
			webSession.login(credentials);
			viewContext.addCallBackParam("ok", true);
		} catch (LoginFailedException ex) {
			viewContext.showGlobalCustomErrorMessage(ex.getMessage(), "");
		}
	}

	/**
	 * Getter for credentials
	 * 
	 * @return
	 */
	public CredentialVO getCredentials() {
		return credentials;
	}

	public String getPostScript() {
		return postScript;
	}

	public void setPostScript(String postScript) {
		this.postScript = postScript;
	}
}
