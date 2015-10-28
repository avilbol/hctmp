/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.wcm.TemporalPublisherUser;

/**
 *
 * @author David Mantilla
 */
public interface UserServices {

    /**
     * Finds a user by email
     *
     * @param email
     * @return
     */
    public UserVO find(String email);

    /**
     * Finds a user by its id
     *
     * @param id
     * @return
     */
    public UserVO find(long id);

    /**
     *
     * @param publisherUser
     * @throws ServiceException
     */
    public void savePropertyPublisher(TemporalPublisherUser publisherUser)
            throws ServiceException;

}
