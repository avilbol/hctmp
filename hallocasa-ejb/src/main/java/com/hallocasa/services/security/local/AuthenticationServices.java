/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.security.local;

import com.hallocasa.commons.exceptions.services.InactiveUserException;
import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.exceptions.services.InvalidPasswordLoginException;
import com.hallocasa.commons.vo.AppAccessInfoVO;
import com.hallocasa.commons.vo.AuthInfoVO;
import com.hallocasa.commons.vo.CredentialVO;

/**
 *
 * @author david
 */
public interface AuthenticationServices {
    
    /**
     * Return the result of the user authentication process
     *
     * @param credentials
     * @return
     * @throws InvalidEmailException When the email doesn't exist
     * @throws InvalidPasswordLoginException When the password doesn't belong to
     * user
     * @throws com.hallocasa.commons.exceptions.services.InactiveUserException
     */
    public AuthInfoVO authenticate(CredentialVO credentials) throws
            InvalidEmailException, InvalidPasswordLoginException,
            InactiveUserException;

    /**
     * Return access information to the application
     *
     * @param userId Id of the user to read access from
     * @return
     */
    public AppAccessInfoVO getAppAccessInfo(long userId);
    
}
