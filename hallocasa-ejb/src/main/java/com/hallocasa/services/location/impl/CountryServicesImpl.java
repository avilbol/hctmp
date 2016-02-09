/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.location.impl;

import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.dataentities.app.City;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.State;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.location.local.CountryServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@Stateless
public class CountryServicesImpl implements CountryServices {

    @EJB
    private AppPersistenceServices appPersistenceServices;

    /**
     * Default constructor
     */
    public CountryServicesImpl() {
    }

    /**
     * Constructor for dependencies injection
     *
     * @param appPersistenceServices
     */
    public CountryServicesImpl(AppPersistenceServices appPersistenceServices) {
        this.appPersistenceServices = appPersistenceServices;
    }

    @Override
    public List<CountryVO> getCountries() {
        List<Country> countries = appPersistenceServices.executeNamedQuery(
                Country.QUERY_FIND_ALL, null, Country.class);

        List<CountryVO> result = ParsersContext.COUNTRY_VO_PARSER.
                toValueObjectList(countries, CountryVO.class);
        return result;
    }

    @Override
    public List<StateVO> getStates(Long countryId) {
        // finds country
        Country country = appPersistenceServices.findEntity(Country.class, countryId);
        if (country == null) {
            throw new IllegalArgumentException("Country with id " + countryId
                    + " doesn't exist");
        }
        List<State> states = appPersistenceServices.executeNamedQuery(
                State.QUERY_FIND_BY_COUNTRY, new Object[]{country}, State.class);
        List<StateVO> stateVOs = ParsersContext.STATE_VO_PARSER.toValueObjectList(
                states, StateVO.class);
        return stateVOs;
    }
    
    @Override
    public List<CityVO> getCities(Long stateId) {
        // finds state
        State state = appPersistenceServices.findEntity(State.class, stateId);
        if (state == null) {
            throw new IllegalArgumentException("State with id " + stateId
                    + " doesn't exist");
        }
        List<City> cities = appPersistenceServices.executeNamedQuery(
                City.QUERY_FIND_BY_STATE, new Object[]{state}, City.class);
        List<CityVO> cityVOs = ParsersContext.CITY_VO_PARSER.toValueObjectList(
               cities, CityVO.class);
        return cityVOs;
    }

}
