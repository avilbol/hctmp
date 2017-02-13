/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.security;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

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
     * @throws OAuthSystemException when error in OAuth authentication
     * @return
     */
    public AuthInfo authenticate(UserCredentials credentials) 
    		throws OAuthSystemException;
    
}
