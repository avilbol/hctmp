/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.commons.exceptions.services.InactiveUserException;
import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.exceptions.services.InvalidPasswordLoginException;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.vo.AppAccessInfoVO;
import com.hallocasa.commons.vo.AuthInfoVO;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.dataentities.wcm.TemporalPublisherUser;

/**
 *
 * @author David Mantilla
 */
public interface UserServices {

    /**
     *
     * @param publisherUser
     * @throws ServiceException
     */
    public void savePropertyPublisher(TemporalPublisherUser publisherUser)
            throws ServiceException;


}
