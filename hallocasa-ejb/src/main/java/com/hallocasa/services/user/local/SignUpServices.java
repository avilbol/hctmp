/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.local;

import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.vo.RegisterUserVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface SignUpServices {

    /**
     * Sends an email to allow user to activate his account.
     *
     * @param userId Id of the account to activate
     * @param activationLink URL with the link to activate user
     * @param activationKey Activation code
     * @throws MailServicesErrorException When the mail sending fails
     */
    public void sendActivationLinkEmail(long userId, String activationLink,
            String activationKey) throws MailServicesErrorException;

    /**
     * Active a user. Normally this method should be used when the user has
     * confirmed email after signed up
     *
     * @param userId
     */
    public void activateUser(long userId);

    /**
     * Register a new user
     *
     * @param registerUserVO
     * @return The new created user
     * @throws com.hallocasa.commons.exceptions.services.InvalidEmailException
     * when the email already exists in the database
     */
    public UserVO registerUser(RegisterUserVO registerUserVO) throws
            InvalidEmailException;

}
