/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.location.impl;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.dataentities.app.Country;
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

}
