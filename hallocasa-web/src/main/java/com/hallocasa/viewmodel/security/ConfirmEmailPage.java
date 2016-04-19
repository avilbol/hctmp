/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.security;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;

/**
 *
 * @author david
 */
@ManagedBean(name = "confirmEmailPage")
@ViewScoped
public class ConfirmEmailPage {

    @Inject
    private ViewContext viewContext;
    @Inject
    private NavigationHandler navigationHandler;
    @EJB
    private SignUpServices signUpServices;
    @EJB
    private UserServices userServices;
    @Inject
    private WebSession webSession;
    
    private UserVO user;

    @PostConstruct
    public void initialize() {
        String email = navigationHandler.getPageParams().get(
                ViewParamEnum.EMAIL.getParamKey());
        String token = navigationHandler.getPageParams().get(
                ViewParamEnum.TOKEN.getParamKey());

        // All parameters are required, if some is missing then is forbidden
        if ((email == null) || (token == null)) {
            throw new ForbiddenException();
        }

        // validates email
        Long userId;
        UserVO userVO;
        user = userServices.find(email);
        if (user == null) {
            throw new ForbiddenException("");
        }

        // validates token
        String genToken = UserActivationLinkUtils.generateActivationKey(user.getId(),
                email);
        if (!genToken.equals(token)) {
            throw new ForbiddenException();
        }

        // activate user
        signUpServices.activateUser(user.getId());
    }

    /**
     * Method only for force bean creation
     *
     * @return
     */
    public String launch() {
        return "";
    }

    /**
     * Process event of going to login
     */
    public void processLoginClick() {
    	webSession.login(user);
        navigationHandler.redirectToPage(HallocasaViewEnum.HOME);
    }

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
}
