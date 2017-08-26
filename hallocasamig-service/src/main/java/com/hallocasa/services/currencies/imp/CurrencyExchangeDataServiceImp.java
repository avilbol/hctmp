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
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCurrencyExchangeData;
import com.hallocasa.entities.EntityCurrencyExchangeData;
import com.hallocasa.jsonmanager.JsonManager;
import com.hallocasa.services.currencies.CurrencyExchangeDataConverter;
import com.hallocasa.services.currencies.CurrencyExchangeDataService;
import com.hallocasa.services.currencies.CurrencyService;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.Currency;

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
	public Map<String, Map<String, Double>> findExchangeRates() {
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
			daoCurrencyExchangeData.clean();
		} catch(IOException e){
			throw new FatalException("Unexpected error", e);
		}
	}
	
	@Override
	public Optional<Double> findRate(Integer currencyFromId, Integer currencyToId) {
		return daoCurrencyExchangeData.findRate(currencyFromId, currencyToId);
	}
	
	private Map<String, Map<String, Double>> toSummary(List<EntityCurrencyExchangeData> exchangeList) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String, Map<String, Double>> summary = new HashMap<>();
		for(EntityCurrencyExchangeData exchangeData : exchangeList){
			String from = exchangeData.getCurrencyFrom().getAbbreviation();
			String to = exchangeData.getCurrencyTo().getAbbreviation();
			Map<String, Double> exchangeMap = summary.get(from);
			exchangeMap = exchangeMap == null ? new HashMap<String, Double>() : exchangeMap;
			exchangeMap.put(to, exchangeData.getRateExchange());
			summary.put(from, exchangeMap);
		}
		return summary;
	}
}
