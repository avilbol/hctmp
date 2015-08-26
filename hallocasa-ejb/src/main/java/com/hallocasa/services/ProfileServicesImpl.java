/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.commons.vo.ProfileVO;
import com.hallocasa.services.interfaces.ProfileServices;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@Stateless
public class ProfileServicesImpl implements ProfileServices{

    @Override
    public ProfileVO find(long id) {
        return new ProfileVO();
    }
    
}
