/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.commons.vo.ProfileVO;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface ProfileServices {

    /**
     * Finds a profile given its id
     *
     * @param id
     * @return
     */
    public ProfileVO find(long id);
}
