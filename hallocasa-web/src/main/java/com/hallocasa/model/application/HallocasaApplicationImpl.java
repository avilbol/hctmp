/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.CountryTelephonePrefixVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.interfaces.FileServicesInterface;
import com.hallocasa.services.interfaces.ImageServicesInterface;
import com.hallocasa.services.location.local.TelephoneServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.persistence.local.WcmPersistenceServices;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "applicationContext", eager = true)
@ApplicationScoped
public class HallocasaApplicationImpl implements HallocasaApplication, 
        Serializable {

    /**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 8457712823542185146L;
	
	@EJB
    @Deprecated
    private WcmPersistenceServices persistenceServices;
    @EJB
    @Deprecated
    private ImageServicesInterface imageServices;
    @EJB
    @Deprecated
    private FileServicesInterface fileServices;
    
    @EJB
    private AppPersistenceServices appPersistenceServices;
    @EJB
    private TelephoneServices telephoneServices;
    
    private List<Language> languages;
    
    private List<CountryVO> countries;
    
    private List<UserTypeVO> userTypes;
    
    private List<CountryTelephonePrefixVO> countryTelephonePrefixList;
    
    private Integer userIdInRecoveryProcess;

    /**
     * Getter for the current instance of the application context
     *
     * @return
     */
    public static HallocasaApplicationImpl getInstance() {
        return getInstance(FacesContext.getCurrentInstance());
    }

    /**
     *
     * @param facesContext
     * @return
     */
    public static HallocasaApplicationImpl getInstance(FacesContext facesContext) {
        return facesContext.getApplication()
                .evaluateExpressionGet(facesContext, "#{applicationContext}",
                        HallocasaApplicationImpl.class);
    }

    @PostConstruct
    public void initialize() {
        languages = new ArrayList<>();
        languages.addAll(Arrays.asList(Language.values())); 
        List<Country> rawCountries = appPersistenceServices.executeNamedQuery(
                Country.QUERY_FIND_ALL, null, Country.class);
        countries = ParsersContext.COUNTRY_VO_PARSER.
                toValueObjectList(rawCountries, CountryVO.class);
        List<UserType> rawUserTypes = appPersistenceServices.executeNamedQuery(
                UserType.QUERY_FIND_ALL, null, UserType.class);
        userTypes = ParsersContext.USER_TYPE_VO_PARSER.
                toValueObjectList(rawUserTypes, UserTypeVO.class);
        countryTelephonePrefixList = telephoneServices.getCountryPrefixList();
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
        return userTypes;
    }

    public void setUserTypes(List<UserTypeVO> userTypes) {
        this.userTypes = userTypes;
    }
 
	public List<CountryTelephonePrefixVO> getCountryTelephonePrefixList() {
		return countryTelephonePrefixList;
	}

	public void setCountryTelephonePrefixList(List<CountryTelephonePrefixVO> countryTelephonePrefixList) {
		this.countryTelephonePrefixList = countryTelephonePrefixList;
	}

	/**
     * @return the databaseServices
     */
    @Deprecated
    public WcmPersistenceServices getPersistenceServices() {
        return persistenceServices;
    }

    /**
     * Getter for the ImageServices EJB
     *
     * @return EJB ImageServices
     */
    @Deprecated
    public ImageServicesInterface getImageServices() {
        return imageServices;
    }

    /**
     * Getter for the ImageServices EJB
     *
     * @return EJB ImageServices
     */
    @Deprecated
    public FileServicesInterface getFileServices() {
        return fileServices;
    }

    /**
     *
     * @return
     */
    public String getVersion() {
        return "0.0.0.1";
    }

    /**
     * Return the list of available languages
     * @return 
     */
    public List<Language> getLanguages() {
        return languages;
    }

    public AppPersistenceServices getAppPersistenceServices() {
        return appPersistenceServices;
    }

    public void setAppPersistenceServices(AppPersistenceServices appPersistenceServices) {
        this.appPersistenceServices = appPersistenceServices;
    }

    public Integer getUserIdInRecoveryProcess() {
        return this.userIdInRecoveryProcess;
    }

    public void setUserIdInRecoveryProcess(Integer userIdInRecoveryProcess) {
        this.userIdInRecoveryProcess = userIdInRecoveryProcess;
    }
}
