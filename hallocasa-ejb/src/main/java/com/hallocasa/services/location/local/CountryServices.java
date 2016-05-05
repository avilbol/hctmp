/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.location.local;

import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.StateVO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface CountryServices {

    /**
     * Return the list of all countries
     *
     * @return
     */
    public List<CountryVO> getCountries();

    /**
     * Return the list of all states given a country
     *
     * @param countryId
     * @return
     */
    public List<StateVO> getStates(Long countryId);
    
    /**
     * Return the list of all cities given a state
     * 
     * @param stateId
     * @return 
     */
    public List<CityVO> getCities(Long stateId);
}
