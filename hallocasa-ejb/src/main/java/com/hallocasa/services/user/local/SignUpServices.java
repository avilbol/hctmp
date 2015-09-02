/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.local;

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

}
