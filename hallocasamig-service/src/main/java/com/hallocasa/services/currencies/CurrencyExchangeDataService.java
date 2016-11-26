package com.hallocasa.services.currencies;

import java.util.Optional;

import com.hallocasa.vo.CurrencyExchangeDataSummary;

/**
 * Contract for service that manage exchange rates
 * @author avillamil
 */
public interface CurrencyExchangeDataService {

	public CurrencyExchangeDataSummary findExchangeRates();

	public void refreshExchangeRates();
	
	/**
	 * Find the rate exchange between two currencies
	 * @param currencyFromId
	 * 		Currency to calculate rate
	 * @param currencyToId
	 * 		Currency reference
	 */
	public Optional<Double> findRate(Integer currencyFromId, Integer currencyToId);
}
