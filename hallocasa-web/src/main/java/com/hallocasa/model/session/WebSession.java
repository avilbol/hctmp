/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.session;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.commons.vo.UserVO;

/**
 * Web session
 *
 * @author David Mantilla
 */
public interface WebSession {

    /**
     * Change language
     *
     * @param language
     */
    public void changeLanguage(Language language);

    /**
     * Returns the current language
     *
     * @return
     */
    public Language getCurrentLanguage();


    /**
     * Login into the session.
     *
     * @param credentialVO
     * @throws LoginFailedException when the login process fail for credentials
     * or any other business logic reason
     */
    public void login(CredentialVO credentialVO) throws LoginFailedException;

    /**
     * Logout
     */
    public void logout();

    /**
     * Return true if the user is already logged into this session
     *
     * @return
     */
    public boolean isLogged();

    /**
     * Getter for current user
     *
     * @return current user
     */
    public UserVO getCurrentUser();

    /**
     * Login into the session with precharged user information.
     *
     * @param userVO
     */
	void login(UserVO userVO);

}
