/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.application;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.joda.time.DateTimeComparator;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.exceptions.services.ErrorJsonResponseException;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CountryTelephonePrefixVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.dataentities.app.City;
import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.dataentities.app.properties.PropertyLocation;
import com.hallocasa.dataentities.app.properties.PropertyProposal;
import com.hallocasa.dataentities.app.properties.PropertyType;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.interfaces.CurrencyExchangeDataServices;
import com.hallocasa.services.interfaces.CurrencyServices;
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
public class HallocasaApplicationImpl implements HallocasaApplication, Serializable {

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

	@EJB
	private CurrencyServices currencyServices;

	@EJB
	private CurrencyExchangeDataServices currencyExchangeDataServices;

	private List<Language> languages;

	private List<CountryVO> countries;

	private Map<Long, MultiLanguageText> cityMap;

	private Map<Long, BigDecimal> cityLatMap;

	private Map<Long, BigDecimal> cityLngMap;

	private List<UserTypeVO> userTypes;

	private List<PropertyTypeVO> propertyTypes;

	private List<CurrencyVO> currencies;

	private List<PropertyLocationVO> propertyLocations;

	private List<PropertyProposalVO> propertyProposals;

	private List<CountryTelephonePrefixVO> countryTelephonePrefixList;

	private Map<String, Object> conversionExchangeRateList;

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
		return facesContext.getApplication().evaluateExpressionGet(facesContext, "#{applicationContext}",
				HallocasaApplicationImpl.class);
	}

	@PostConstruct
	public void initialize() {
		languages = new ArrayList<>();
		languages.addAll(Arrays.asList(Language.values()));
		List<Country> rawCountries = appPersistenceServices.executeNamedQuery(Country.QUERY_FIND_ALL, null,
				Country.class);
		propertyTypes = ParsersContext.PROPERTY_TYPE_VO_PARSER.toValueObjectList(
				appPersistenceServices.executeNamedQuery(PropertyType.QUERY_FIND_ALL, null, PropertyType.class),
				PropertyTypeVO.class);
		propertyProposals = ParsersContext.PROPERTY_PROPOSAL_VO_PARSER.toValueObjectList(
				appPersistenceServices.executeNamedQuery(PropertyProposal.QUERY_FIND_ALL, null, PropertyProposal.class),
				PropertyProposalVO.class);
		propertyLocations = ParsersContext.PROPERTY_LOCATION_VO_PARSER.toValueObjectList(
				appPersistenceServices.executeNamedQuery(PropertyLocation.QUERY_FIND_ALL, null, PropertyLocation.class),
				PropertyLocationVO.class);
		countries = ParsersContext.COUNTRY_VO_PARSER.toValueObjectList(rawCountries, CountryVO.class);

		List<City> cities = appPersistenceServices.executeNamedQuery(City.QUERY_FIND_ALL, null, City.class);
		cityMap = new HashMap<>();
		cityLatMap = new HashMap<>();
		cityLngMap = new HashMap<>();
		for (City city : cities) {
			cityMap.put(city.getId(), city.getCityName());
			cityLatMap.put(city.getId(), city.getDefaultLatCoordinate());
			cityLngMap.put(city.getId(), city.getDefaultLngCoordinate());
		}

		List<UserType> rawUserTypes = appPersistenceServices.executeNamedQuery(UserType.QUERY_FIND_ALL, null,
				UserType.class);
		userTypes = ParsersContext.USER_TYPE_VO_PARSER.toValueObjectList(rawUserTypes, UserTypeVO.class);
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
	 * 
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

	public List<PropertyTypeVO> getPropertyTypes() {
		return propertyTypes;
	}

	public void setPropertyTypes(List<PropertyTypeVO> propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	public List<PropertyLocationVO> getPropertyLocations() {
		return propertyLocations;
	}

	public void setPropertyLocations(List<PropertyLocationVO> propertyLocations) {
		this.propertyLocations = propertyLocations;
	}

	public List<PropertyProposalVO> getPropertyProposals() {
		return propertyProposals;
	}

	public void setPropertyProposals(List<PropertyProposalVO> propertyProposals) {
		this.propertyProposals = propertyProposals;
	}

	public List<CurrencyVO> getCurrencies() {
		if (currencies == null || currencies.isEmpty()) {
			currencies = currencyServices.find();
		}
		return currencies;
	}

	public void setCurrencies(List<CurrencyVO> currencies) {
		this.currencies = currencies;
	}

	public Map<Long, MultiLanguageText> getCityMap() {
		return cityMap;
	}

	public Map<Long, BigDecimal> getCityLatMap() {
		return cityLatMap;
	}

	public Map<Long, BigDecimal> getCityLngMap() {
		return cityLngMap;
	}

	public Map<String, Object> getConversionExchangeRateList() {
		Date lastUpdate = currencyExchangeDataServices.findLastUpdate();
		int compareResult = -1;
		if(lastUpdate != null){
			compareResult = DateTimeComparator.getDateOnlyInstance().compare(lastUpdate, new Date());
		}
		if (compareResult < 0) {
			try {
				conversionExchangeRateList = currencyExchangeDataServices.refreshWithLiveRates();
			} catch (ErrorJsonResponseException e) {
				// TODO: set error here
			}
		}
		if (conversionExchangeRateList == null || conversionExchangeRateList.isEmpty()){
			conversionExchangeRateList = currencyExchangeDataServices.find();
		}
		return conversionExchangeRateList;
	}

	public void setConversionExchangeRateList(Map<String, Object> conversionExchangeRateList) {
		this.conversionExchangeRateList = conversionExchangeRateList;
	}
	
	/**
	 * Get the exchange rate between two currencies
	 * @param currencyFrom
	 * @param currencyTo
	 * @return
	 */
	public Double getRateExchange(CurrencyVO currencyFrom, CurrencyVO currencyTo){
		Map<Integer, CurrencyVO> currencyMap = new HashMap<>();
		for(CurrencyVO cvo : getCurrencies()){
			currencyMap.put(cvo.getId(), cvo);
		}
		Integer idCurrencyFrom = currencyFrom.getId();
		Integer idCurrencyTo = currencyTo.getId();
		String currencyFromAb = currencyMap.get(idCurrencyFrom).getAbbreviation();
		String currencyToAb = currencyMap.get(idCurrencyTo).getAbbreviation();
		String fullAb = currencyFromAb.trim() + currencyToAb.trim();
		Map<String, Object> conversionExchangeRateList = getConversionExchangeRateList();
		return (Double) conversionExchangeRateList.get(fullAb);
	}
	
	/**
	 * Get converted value of a currency value
	 * @param value
	 * 		The value to convert
	 * @param currencyFrom
	 * 		Currency source
	 * @param currencyTo
	 * 		Currency destiny
	 * @return
	 * 		The value expressed in currency destiny
	 */
	public Double getConvertedValue(BigDecimal value, CurrencyVO currencyFrom, CurrencyVO currencyTo){
		if(currencyTo.getAbbreviation().equals("USD")){
			Double rate = getRateExchange(currencyTo, currencyFrom);
			return value.multiply(new BigDecimal(1/rate)).doubleValue();
		}
		Double rate = getRateExchange(currencyFrom, currencyTo);
		if(rate != null){
			return value.multiply(new BigDecimal(rate)).doubleValue();
		}
		Map<String, CurrencyVO> currencyMap = new HashMap<>();
		for(CurrencyVO cvo : getCurrencies()){
			currencyMap.put(cvo.getAbbreviation(), cvo);
		}
		CurrencyVO usdCurrency = currencyMap.get("USD");
		Double rateBridge = getRateExchange(usdCurrency, currencyFrom);
		Double rateFinal = getRateExchange(usdCurrency, currencyTo);
		BigDecimal intermediaryValue = value.multiply(new BigDecimal(1/rateBridge));
		BigDecimal finalValue = intermediaryValue.multiply(new BigDecimal(rateFinal));
		return finalValue.doubleValue();
	}

}
