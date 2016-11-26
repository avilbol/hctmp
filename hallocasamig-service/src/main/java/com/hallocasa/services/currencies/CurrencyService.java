package com.hallocasa.services.currencies;

import java.util.List;

import com.hallocasa.vo.Currency;

public interface CurrencyService {

	/**
	 * Find all currencies available in system
	 */
	public List<Currency> find();
}
