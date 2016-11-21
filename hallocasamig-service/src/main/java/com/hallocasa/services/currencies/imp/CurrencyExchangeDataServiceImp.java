package com.hallocasa.services.currencies.imp;

import static com.hallocasa.api.CurrencyLayerApiComunication.callMethod;
import static com.hallocasa.systemproperties.SystemConstants.CURRENCY_LAYER_API_KEY;
import static com.hallocasa.systemproperties.SystemConstants.CURRENCY_LAYER_LIVE_RESOURCE;
import static com.hallocasa.systemproperties.SystemConstants.CURRENCY_LAYER_URL;
import static com.hallocasa.systemproperties.SystemProperty.get;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.hallocasa.dao.i.IDAOCurrencyExchangeData;
import com.hallocasa.entities.EntityCurrency;
import com.hallocasa.entities.EntityCurrencyExchangeData;
import com.hallocasa.jsonmanager.JsonManager;
import com.hallocasa.services.currencies.CurrencyExchangeDataConverter;
import com.hallocasa.services.currencies.CurrencyExchangeDataService;
import com.hallocasa.services.currencies.CurrencyService;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.Currency;
import com.hallocasa.vo.CurrencyExchangeData;
import com.hallocasa.vo.CurrencyExchangeDataSummary;

@Stateless
public class CurrencyExchangeDataServiceImp implements CurrencyExchangeDataService {

	@EJB
	CurrencyExchangeDataConverter exchangeDataConverter;
	
	@EJB
	CurrencyService currencyService;
	
	@EJB
	IDAOCurrencyExchangeData daoCurrencyExchangeData;
	
	/**
	 * Name of property for query string key 'quotes'
	 */
	private static final String QUOTES_PROPERTY_NAME = "quotes";
	
	@Override
	public CurrencyExchangeDataSummary findExchangeRates() {
		if(!daoCurrencyExchangeData.isTodayUpdated()){
			refreshExchangeRates();
		}
		try{
			return toSummary(daoCurrencyExchangeData.find());
		} catch(IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
			throw new FatalException("Unexpected error. " + e);
		}
		
	}

	@Override
	public void refreshExchangeRates() {
		try{
			Map<String, String> data;
			data = new HashMap<>();
			data.put("access_key", get(CURRENCY_LAYER_API_KEY));
			String response = callMethod(get(CURRENCY_LAYER_URL), get(CURRENCY_LAYER_LIVE_RESOURCE), data);
			Map<String, Object> currencyExchangeData = JsonManager.fromJson(response, QUOTES_PROPERTY_NAME);
			List<Currency> currencies = currencyService.find();
			List<EntityCurrencyExchangeData> exchangeList = exchangeDataConverter
					.toSimpleList(currencyExchangeData, currencies);
			daoCurrencyExchangeData.save(exchangeList);
		} catch(IOException e){
			throw new FatalException("Unexpected error", e);
		}
	}
	
	private CurrencyExchangeDataSummary toSummary(List<EntityCurrencyExchangeData> exchangeList) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CurrencyExchangeDataSummary summary = new CurrencyExchangeDataSummary();
		summary.setAUD(new CurrencyExchangeData());
		summary.setCOP(new CurrencyExchangeData());
		summary.setGBP(new CurrencyExchangeData());
		summary.setCAD(new CurrencyExchangeData());
		summary.setCHF(new CurrencyExchangeData());
		summary.setUSD(new CurrencyExchangeData());
		summary.setEUR(new CurrencyExchangeData());
		for(EntityCurrencyExchangeData exchangeData : exchangeList){
			EntityCurrency crcyFrom = exchangeData.getCurrencyFrom();
			EntityCurrency crcyTo = exchangeData.getCurrencyTo();
			CurrencyExchangeData crcyExData = (CurrencyExchangeData) PropertyUtils.getProperty(summary, crcyFrom.getAbbreviation());
			BeanUtils.setProperty(crcyExData, crcyTo.getAbbreviation(), exchangeData.getRateExchange());
		}
		return summary;
	}
}
