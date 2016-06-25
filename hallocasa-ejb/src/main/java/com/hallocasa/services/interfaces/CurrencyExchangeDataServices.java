package com.hallocasa.services.interfaces;

import static com.hallocasa.commons.constants.SystemConstants.CURRENCY_LAYER_API_KEY;
import static com.hallocasa.commons.constants.SystemConstants.CURRENCY_LAYER_API_RESOURCE;
import static com.hallocasa.commons.constants.SystemConstants.CURRENCY_LAYER_API_URL;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.hallocasa.commons.exceptions.services.ErrorJsonResponseException;
import com.hallocasa.commons.jsonmanager.JsonManager;
import com.hallocasa.commons.restcom.ApiComunication;
import com.hallocasa.dataentities.app.CurrencyExchangeData;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.helpers.CurrencyExchangeDataParser;
import com.hallocasa.services.persistence.local.AppPersistenceServices;

/**
 * Service for provide currency exchange data functionality
 * 
 * @author Alexander Villamil
 *
 */
public class CurrencyExchangeDataServices {

	/**
	 * Name of property for query string key 'access_key'
	 */
	private static final String ACCESS_KEY_PROPERTY_NAME = "access_key";

	/**
	 * Name of property for query string key 'quotes'
	 */
	private static final String QUOTES_PROPERTY_NAME = "quotes";

	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@EJB
	private CurrencyServices currencyServices;

	/**
	 * Call the API in order to refresh rates
	 * @throws Exception 
	 */
	public Map<String, Object> refreshWithLiveRates() throws ErrorJsonResponseException{
		try{
			Map<String, String> data = new HashMap<String, String>();
			data.put(ACCESS_KEY_PROPERTY_NAME, CURRENCY_LAYER_API_KEY);
			String response = ApiComunication.callMethod(CURRENCY_LAYER_API_URL, 
					CURRENCY_LAYER_API_RESOURCE, data);
			Map<String, Object> currencyExchangeData = JsonManager.fromJson(response, QUOTES_PROPERTY_NAME);
			currencyServices.persistExchangeData(currencyExchangeData);
			return currencyExchangeData;
		} catch(IOException e){
			throw new RuntimeException("Problem with trying to get new rates data.", e);
		}
	}

	/**
	 * Return the last update of the currency exchange data elements
	 * @return
	 */
	public Date findLastUpdate(){
		try{
			return appPersistenceServices.executeQuery(
					CurrencyExchangeData.QUERY_LAST_UPDATE, Date.class);
		} catch(NoResultException e){
			return null;
		}
		
	}
	
	/**
	 * Finds the overall list of conversions saved in system
	 * @return
	 */
	public Map<String, Object> find(){
		List<CurrencyExchangeData> cedataList = appPersistenceServices.executeNamedQuery(CurrencyExchangeData.QUERY_FIND_ALL,
				new Object[] {}, CurrencyExchangeData.class);
		return new CurrencyExchangeDataParser().toConversionExchangeMap(cedataList);
	}
	
	/**
	 * Save the list of currency exchange data
	 * 
	 * @param cedataList
	 */
	public void save(List<CurrencyExchangeData> cedataList) {
		appPersistenceServices.mergeEntityList(cedataList);
	}

}
