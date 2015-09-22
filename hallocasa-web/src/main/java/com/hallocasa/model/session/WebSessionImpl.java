/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.session;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.commons.vo.AuthInfoVO;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.commons.vo.ProfileVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.controlaccess.AccessValidator;
import com.hallocasa.services.interfaces.ProfileServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.navigation.NavigationHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletRequest;

/**
 *
 * @author David Mantilla
 */
@Named("webSession")
@SessionScoped
public class WebSessionImpl extends Observable implements WebSession,
        Serializable {

    /* Instance variables */
    private Language currentLanguage;
    private Map<String, Object> attributesMap;
    private UserVO currentUser;

    /* dependencies */
    @EJB
    private UserServices userServices;
    @EJB
    private ProfileServices profileServices;
    @Inject
    private AccessValidator accessValidator;
    @Inject
    private NavigationHandler navigationHandler;

    /* instance variables */
    private AuthInfoVO authInfoVO;
    private List<ProfileVO> currentProfiles;

    //private OneForSession oneForSession;
    /**
     * Post construct
     */
    @PostConstruct
    public void initialize() {
    }

    /**
     * Returns the current language
     *
     * @return
     */
    @Override
    public Language getCurrentLanguage() {
        return currentLanguage;
    }

    /**
     * Change language
     *
     * @param language
     */
    @Override
    public void changeLanguage(Language language) {
        currentLanguage = language;
    }

    /**
     * Return the current instance of the webSession
     *
     * @return
     */
    @Deprecated
    public static WebSession
            getCurrentInstance() {
        return CDI.current().select(WebSession.class
        ).get();
    }

    @Override
    public void login(CredentialVO credentialVO) throws LoginFailedException {

        if (isLogged()) {
            logout();
        }

        /*   authInfoVO = null;
         try {
         authInfoVO = userServices.authenticate(credentialVO);
         currentUser = authInfoVO.getAccount();
         accessValidator.clear();
         currentLanguage = null; // force local reloading
         } catch (InvalidEmailException e) {
         throw new LoginFailedException(Messages
         .getString(Messages.LOGIN_INVALID_EMAIL_MESSAGE));
         } catch (InvalidPasswordLoginException e) {
         throw new LoginFailedException(Messages
         .getString(Messages.LOGIN_INVALID_PASSWORD_MESSAGE));
         } catch (InactiveUserException e) {
         throw new LoginFailedException(Messages
         .getString(Messages.LOGIN_INACTIVE_USER_MESSAGE));
         } */
    }

    @Override
    public boolean isLogged() {
        return currentUser.getId() != null;
    }

    @Override
    public void logout() {
        initVariables();
        loadPublicUser();
        accessValidator.clear();
        deleteObservers();
    }

    /**
     * Initialize variables with the right value
     */
    private void initVariables() {
        this.authInfoVO = null;
        this.attributesMap = new HashMap<>();
    }

    /**
     * Load Public User
     */
    private void loadPublicUser() {
        // Initialize a public user
        currentUser = new UserVO();
        currentUser.setFirstName("Public");
        currentUser.setLastName("User");
        currentProfiles = new ArrayList<>();
        currentProfiles.add(profileServices.find(SystemConstants.PUBLIC_PROFILE_ID));
        currentProfiles = java.util.Collections.unmodifiableList(currentProfiles);
    }

    /**
     * Return The current social session
     *
     * @return The current social session
     */
    public static WebSession
            getCurrent() {
        return CDI.current().select(WebSession.class
        ).get();
    }

    /**
     * Getter for current user
     *
     * @return current user
     */
    @Override
    public UserVO getCurrentUser() {
        return currentUser;
    }

}
