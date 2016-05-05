/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.globalapp.impl;


import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.globalapp.GlobalAppServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author avillamil
 */
@ManagedBean(name="globalApp", eager=true)
@ApplicationScoped
@Stateless
public class GlobalAppServicesImpl implements GlobalAppServices{
    
    List<CountryVO> countries;
    
    List<UserTypeVO> userTypes;
    
    @EJB
    private AppPersistenceServices appPersistenceServices;

    @PostConstruct
    public void initialize(){
    }
    
    @Override
    public List<CountryVO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryVO> countries) {
        this.countries = countries;
    }

    @Override
    public List<UserTypeVO> getUserTypes() {
         List<UserType> rawUserTypes = appPersistenceServices.executeNamedQuery(
                UserType.QUERY_FIND_ALL, null, UserType.class);
        userTypes = ParsersContext.USER_TYPE_VO_PARSER.
                toValueObjectList(rawUserTypes, UserTypeVO.class);
        return userTypes;
    }

    public void setUserTypes(List<UserTypeVO> userTypes) {
        this.userTypes = userTypes;
    }
    
    
    
}
