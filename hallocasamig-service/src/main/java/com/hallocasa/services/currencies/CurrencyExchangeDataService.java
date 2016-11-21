package com.hallocasa.services.currencies;

import java.util.List;

import com.hallocasa.vo.CurrencyExchangeDataSummary;

/**
 * Contract for service that manage exchange rates
 * @author avillamil
 */
public interface CurrencyExchangeDataService {

	List<CurrencyExchangeDataSummary> findExchangeRates();

	boolean refreshExchangeRates();
}
