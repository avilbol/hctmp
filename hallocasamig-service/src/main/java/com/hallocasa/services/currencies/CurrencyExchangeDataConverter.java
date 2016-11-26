package com.hallocasa.services.currencies;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.hallocasa.entities.EntityCurrency;
import com.hallocasa.entities.EntityCurrencyExchangeData;
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.Currency;

/**
 * Parser for exchange data in currencies.
 * 
 * @author Alexander Villamil
 */
@Stateless
public class CurrencyExchangeDataConverter {

	/**
	 * Process the conversion exchange map in order to generate a simple list of
	 * currency exchange data (to persist in database after)
	 * 
	 * @param conversionExchangeMap
	 *            Map to process
	 * @param currencies
	 *            Currencies of reference
	 * @return
	 */
	public List<EntityCurrencyExchangeData> toSimpleList(Map<String, Object> conversionExchangeMap,
			List<Currency> currencies) {
		List<EntityCurrencyExchangeData> cedataList = new ArrayList<>();	
		for (Currency crcyFrom : currencies) {
			for (Currency crcyTo : currencies) {
				EntityCurrencyExchangeData cedata = new EntityCurrencyExchangeData();
				cedata.setCurrencyFrom((EntityCurrency) HallocasaConvert.toEntity(crcyFrom));
				cedata.setCurrencyTo((EntityCurrency) HallocasaConvert.toEntity(crcyTo));
				cedata.setRateExchange(this.rate(conversionExchangeMap, 
						crcyFrom.getAbbreviation(), crcyTo.getAbbreviation()));
				cedata.setUpdateDate(new Date());
				cedataList.add(cedata);
			}
		}
		return cedataList;
	}
	
	
	public Double rate(Map<String, Object> conversionExchangeMap, String prefixFrom, String prefixTo){
		if(prefixFrom.equals(prefixTo)){
			return 1.0;
		}
		boolean usdFromOn = prefixFrom.equals("USD");
		boolean usdToOn = prefixTo.equals("USD");
		Double dblVal = (Double) conversionExchangeMap.get(prefixFrom + prefixTo);
		if(dblVal != null){
			return dblVal;
		}
		if(usdFromOn){
			throw new FatalException("USD conversion not found in currency prefixes.");
		}
		if(usdToOn){
			return 1 / rate(conversionExchangeMap, prefixTo, prefixFrom);
		}
		return ( 1 / rate(conversionExchangeMap, "USD", prefixFrom)) * rate(conversionExchangeMap, "USD", prefixTo);
	}

	/**
	 * Process the conversion exchange data list into a map of conversion exchange
	 * @param cedataList
	 * 	  List of conversion exchange data to be processed
	 * @return
	 */
	public Map<String, Object> toConversionExchangeMap(List<EntityCurrencyExchangeData> cedataList) {
		Map<String, Object> conversionExchangeMap = new HashMap<String, Object>();
		for(EntityCurrencyExchangeData cedata: cedataList){
			String currencyFromAbbr = cedata.getCurrencyFrom().getAbbreviation();
			String currencyToAbbr = cedata.getCurrencyTo().getAbbreviation();
			String fullAbbr = currencyFromAbbr.trim() + currencyToAbbr.trim();
			conversionExchangeMap.put(fullAbbr, cedata.getRateExchange());
		}
		return conversionExchangeMap;
	}
}
