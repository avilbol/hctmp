package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.vo.Currency;

public interface IDAOCurrency {

	/**
	 * Find the currencies supportes by system
	 */
	List<Currency> find();
	
}
