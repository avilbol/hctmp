package com.hallocasa.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.dataentities.app.Currency;
import com.hallocasa.dataentities.app.CurrencyExchangeData;

/**
 * Parser for exchange data in currencies.
 * 
 * @author Alexander Villamil
 */
public class CurrencyExchangeDataParser {

	/**
	 * Start of "currency from" position in abbr
	 */
	private static final int INIT_ABBR_CURRENCY_FROM_POSITION = 0;

	/**
	 * End of "currency from" position in abbr
	 */
	private static final int END_ABBR_CURRENCY_FROM_POSITION = 3;

	/**
	 * Start of "currency to" position in abbr
	 */
	private static final int INIT_ABBR_CURRENCY_TO_POSITION = 3;

	/**
	 * End of "currency to" position in abbr
	 */
	private static final int END_ABBR_CURRENCY_TO_POSITION = 0;

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
	public List<CurrencyExchangeData> toSimpleList(Map<String, Object> conversionExchangeMap,
			List<CurrencyVO> currencies) {
		List<CurrencyExchangeData> cedataList = new ArrayList<>();
		Set<String> keySet = conversionExchangeMap.keySet();
		Map<String, CurrencyVO> currenciesByAbr = new HashMap<String, CurrencyVO>();
		for (CurrencyVO cvo : currencies) {
			currenciesByAbr.put(cvo.getAbbreviation(), cvo);
		}
		for (String key : keySet) {
			CurrencyExchangeData cedata = new CurrencyExchangeData();
			String currencyFromAb = key.substring(INIT_ABBR_CURRENCY_FROM_POSITION, END_ABBR_CURRENCY_FROM_POSITION);
			String currencyToAb = key.substring(INIT_ABBR_CURRENCY_TO_POSITION, END_ABBR_CURRENCY_TO_POSITION);
			CurrencyVO currencyFrom = currenciesByAbr.get(currencyFromAb);
			CurrencyVO currencyTo = currenciesByAbr.get(currencyToAb);
			cedata.setCurrencyFrom(new CurrencyVOParser().toEntity(currencyFrom, Currency.class));
			cedata.setCurrencyTo(new CurrencyVOParser().toEntity(currencyTo, Currency.class));
			cedata.setRateExchange((Double) conversionExchangeMap.get(key));
			cedataList.add(cedata);
		}
		return cedataList;
	}

	/**
	 * Process the conversion exchange data list into a map of conversion exchange
	 * @param cedataList
	 * 	  List of conversion exchange data to be processed
	 * @return
	 */
	public Map<String, Object> toConversionExchangeMap(List<CurrencyExchangeData> cedataList) {
		Map<String, Object> conversionExchangeMap = new HashMap<String, Object>();
		for(CurrencyExchangeData cedata: cedataList){
			String currencyFromAbbr = cedata.getCurrencyFrom().getAbbreviation();
			String currencyToAbbr = cedata.getCurrencyTo().getAbbreviation();
			String fullAbbr = currencyFromAbbr.trim() + currencyToAbbr.trim();
			conversionExchangeMap.put(fullAbbr, cedata.getRateExchange());
		}
		return conversionExchangeMap;
	}
}
