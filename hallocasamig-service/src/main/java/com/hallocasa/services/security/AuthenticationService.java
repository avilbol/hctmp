/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.security;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.hallocasa.utils.constants.exceptions.InvalidEmailException;
import com.hallocasa.utils.constants.exceptions.InvalidPasswordLoginException;
import com.hallocasa.vo.security.AuthInfo;
import com.hallocasa.vo.security.UserCredentials;

/**
 * Services for authenticate the user with appropriate credentials
 */
public interface AuthenticationService {
    
    /**
     * Return the result of the user authentication process
     *
     * @param credentials
     * @return
     * @throws InvalidEmailException When the email doesn't exist
     * @throws InvalidPasswordLoginException When the password doesn't belong to
     * @throws OAuthSystemException when the token generation fails
     */
    public AuthInfo authenticate(UserCredentials credentials) throws
            InvalidEmailException, InvalidPasswordLoginException, OAuthSystemException;
    
}
