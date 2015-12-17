/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.application;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserTypeVO;
import java.util.List;

/**
 *
 * @author david
 */
public interface HallocasaApplication {
    
    List<CountryVO> getCountries();
    
    List<UserTypeVO> getUserTypes();
}
