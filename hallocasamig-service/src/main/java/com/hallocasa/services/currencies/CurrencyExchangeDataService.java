package com.hallocasa.services.currencies;

import java.util.Map;
import java.util.Optional;

/**
 * Contract for service that manage exchange rates
 * @author avillamil
 */
public interface CurrencyExchangeDataService {

	/**
	 * Generates map DTO with exchange rates in system currencies
	 * @return
	 * 		map DTO with exchange rates in system currencies
	 */
	public Map<String, Map<String, Double>> findExchangeRates();

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
