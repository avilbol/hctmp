/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.commons.vo.ProfileVO;
import com.hallocasa.services.interfaces.ProfileServices;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implements ProfileService
 *
 * @author david
 */
@Stateless
public class ProfileServicesImpl implements ProfileServices {

    @EJB
    private PersistenceServicesImpl persistenceServices;

    @Override
    public ProfileVO find(long id) {
        ProfileVO profileVO = new ProfileVO();
        return profileVO;
        // throw new UnsupportedOperationException("Not yet");
        // return persistenceServices.findEntity( Profile.class, id);
    }

}
