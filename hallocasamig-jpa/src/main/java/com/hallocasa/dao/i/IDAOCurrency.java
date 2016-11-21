package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityCurrency;

public interface IDAOCurrency {

	/**
	 * Find the currencies supportes by system
	 */
	List<EntityCurrency> find();
	
}
