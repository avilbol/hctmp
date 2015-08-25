/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.commons.vo.ProfileVO;
import com.hallocasa.services.interfaces.ProfileServices;
import javax.ejb.EJB;

/**
 * Implements ProfileService
 *
 * @author david
 */
public class ProfileServicesImplementation implements ProfileServices {

    @EJB
    private PersistenceServices persistenceServices;

    @Override
    public ProfileVO find(long id) {
        throw new UnsupportedOperationException("Not yet");
        // return persistenceServices.findEntity( Profile.class, id);
    }

}
