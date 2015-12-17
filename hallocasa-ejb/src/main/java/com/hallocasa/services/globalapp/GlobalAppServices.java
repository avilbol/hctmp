/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.globalapp;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserTypeVO;
import java.util.List;

/**
 *
 * @author avillamil
 */
public interface GlobalAppServices {
    
    public List<CountryVO> getCountries();
    
    public List<UserTypeVO> getUserTypes();
}
