package com.hallocasa.dao.i;

import java.util.List;
import java.util.Optional;

import com.hallocasa.entities.EntityCurrencyExchangeData;

public interface IDAOCurrencyExchangeData {

	public List<EntityCurrencyExchangeData> find();
	
	public void save(List<EntityCurrencyExchangeData> exchangeList);
	
	public void clean();
	
	public boolean isTodayUpdated();
	
	/**
	 * Find the exchange rate between two currencies
	 * @param currencyFromId
	 * 		Currency one
	 * @param currencyToId
	 * 		Currency two
	 * @return
	 * 		The exchange rate from currency one to currency two
	 */
	Optional<Double> findRate(Integer currencyFromId, Integer currencyToId);
}
