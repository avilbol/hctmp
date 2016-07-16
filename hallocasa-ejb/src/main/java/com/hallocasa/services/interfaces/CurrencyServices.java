package com.hallocasa.services.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hallocasa.commons.vo.CurrencyVO;

/**
 * Service for provide currency functionality
 * @author avillamil
 *
 */
public interface CurrencyServices {

	/**
	 * Finds currency list
	 */
	public List<CurrencyVO> find();
	
	/**
	 * Persist the info about data exchange
	 * @param exchangeDataMap
	 */
	public void persistExchangeData(Map<String, Object> exchangeDataMap);
	 
	/**
	 * Exchange money value to another money currency value
	 * @param value
	 * 		Original value to exchange
	 * @param currencyFrom
	 * 		Currency of the original value to exchange
	 * @param currencyTo
	 * 		Currency of the exchanged value
	 * @param conversionExchangeRateList
	 * 		Exchange rates of reference
	 * @param currencies
	 * 		Currencies of reference
	 * @return
	 * 		Desired exchanged value
	 */
	public Double exchange(BigDecimal value, CurrencyVO currencyFrom, 
			CurrencyVO currencyTo, Map<String, Object> conversionExchangeRateList,
			List<CurrencyVO> currencies);
}
