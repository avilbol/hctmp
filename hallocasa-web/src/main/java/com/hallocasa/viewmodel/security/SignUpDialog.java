/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.security;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.vo.RegisterUserVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.i18n.Messages;
import com.hallocasa.view.navigation.ViewParamEnum;

/**
 *
 * @author david
 */
@ManagedBean(name = "signUpDialog")
@ViewScoped
public class SignUpDialog {

    /* class variables */
    private static final Logger LOG = Logger.getLogger(SignUpDialog.class.getName());

    /* dependencies */
    @EJB
    private SignUpServices signUpServices;
    @Inject
    private WebSession webSession;
    @Inject
    private ViewContext viewContext;

    /* instance variables */
    private RegisterUserVO registerUserVO;
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 80)
    private String passwordConfirm;

    // Accept terms and conditions
    private boolean acceptTerms;
    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {
        registerUserVO = new RegisterUserVO();
        registerUserVO.setLanguage(webSession.getCurrentLanguage());
    }

    /**
     * Listener for click on submit button
     */
    public void processSubmitClick() {
        if (!validateForm()) {
            return;
        }
        try {
            UserVO userVO = signUpServices.registerUser(registerUserVO);
            Map<String, String> params = new HashMap<>();
            params.put(ViewParamEnum.USER_ID.getParamKey(), userVO.getId().toString());
            String activationUrl = UserActivationLinkUtils.buildUrl(
                    userVO.getEmail(), userVO.getId());
            String activationKey = UserActivationLinkUtils.generateActivationKey(
                    userVO.getId(), userVO.getEmail());
            signUpServices.sendActivationLinkEmail(userVO.getId(), activationUrl, activationKey);
            
            // shows success messages
            viewContext.showGlobalInfoMessage(Messages.SIGNUP_SUCCESS, null);
            viewContext.showGlobalInfoMessage(Messages.SIGNUP_EMAIL_SENT, null);
            viewContext.addCallBackParam("ok", true);
            
        } catch (InvalidEmailException ex) {
            viewContext.showGlobalErrorMessage(Messages.SIGNUP_EMAIL_EXIST, null);
        } catch (MailServicesErrorException ex) {
            viewContext.showGlobalErrorMessage(Messages.UNEXPECTED_ERROR, null);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unexepcted error", e);
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

    /**
     * Getter for passwordConfirm
     *
     * @return
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Setter for passwordConfirm
     *
     * @param passwordConfirm
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public boolean isAcceptTerms() {
		return acceptTerms;
	}

	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}

	/**
     * Execute additional validations, that means other than bean validations
     *
     * @return
     */
    private boolean validateForm() {
    	String message = null;
    	if(!acceptTerms){
    		message= ValidationMessages.MUST_ACCEPT_TERMS_AND_CONDITIONS;
    	}
        if (!passwordConfirm.equals(registerUserVO.getPassword())) {
            message = ValidationMessages.SIGNUP_PASSWORD_CONFIRM_NOT_MATCH;
        }
        if(message != null){
        	viewContext.showGlobalCustomErrorMessage(
                    ValidationMessages.getString(
                            message,
                            webSession.getCurrentLanguage().getLocale()),
                    null);
        	return false;
        }     
        return true;
    }

}
