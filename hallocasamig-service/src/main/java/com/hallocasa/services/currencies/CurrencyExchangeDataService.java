package com.hallocasa.services.currencies;

import com.hallocasa.vo.CurrencyExchangeDataSummary;

/**
 * Contract for service that manage exchange rates
 * @author avillamil
 */
public interface CurrencyExchangeDataService {

	CurrencyExchangeDataSummary findExchangeRates();

	void refreshExchangeRates();
}
