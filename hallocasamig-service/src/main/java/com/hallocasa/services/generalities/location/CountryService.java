package com.hallocasa.services.generalities.location;

import java.util.List;

import com.hallocasa.vo.Country;

public interface CountryService {

	/**
	 * Find all countries in system
	 * @return
	 * 		All countries available in system
	 */
	public List<Country> find();
	
}
