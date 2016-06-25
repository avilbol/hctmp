package com.hallocasa.services.interfaces;

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
	 
}
