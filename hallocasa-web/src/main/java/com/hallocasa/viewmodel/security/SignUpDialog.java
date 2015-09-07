/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.security;

import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.vo.RegisterUserVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.i18n.Messages;
import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.view.navigation.ViewParamEnum;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@ManagedBean("signUpDialog")
@ViewScoped
public class SignUpDialog {

    /* dependencies */
    @EJB
    private SignUpServices signUpServices;
    @Inject
    private WebSession webSession;
    @Inject
    private ViewContext viewContext;
    @Inject
    private NavigationHandler navigationHandler;

    /* instance variables */
    private RegisterUserVO registerUserVO;

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {

    }

    /**
     * Listener for click on submit button
     */
    public void processSubmitClick() {
        try {
            UserVO userVO = signUpServices.registerUser(registerUserVO);
            Map<String, String> params = new HashMap<>();
            params.put(ViewParamEnum.USER_ID.getParamKey(), userVO.getId().toString());
            String activationUrl = UserActivationLinkUtils.buildUrl(
                    userVO.getEmail(), userVO.getId());
            String activationKey = UserActivationLinkUtils.generateActivationKey(
                    userVO.getId(), userVO.getEmail());
            signUpServices.sendActivationLinkEmail(userVO.getId(), activationUrl, activationKey);
        } catch (InvalidEmailException ex) {
            viewContext.showGlobalErrorMessage(Messages.SIGNUP_EMAIL_EXIST, null);
        } catch (MailServicesErrorException ex) {
            viewContext.showGlobalErrorMessage(Messages.UNEXPECTED_ERROR, null);
        }
    }

    /**
     * Getter for credentialVO
     *
     * @return
     */
    public RegisterUserVO getRegisterUserVO() {
        return registerUserVO;
    }

}
