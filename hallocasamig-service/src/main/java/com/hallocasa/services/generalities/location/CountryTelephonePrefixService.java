package com.hallocasa.services.generalities.location;

import java.util.List;

import com.hallocasa.vo.CountryTelephonePrefix;

public interface CountryTelephonePrefixService {

	/**
	 * Find country telephone prefix list in system
	 * @return
	 * 		 country telephone prefix list in system
	 */
	public List<CountryTelephonePrefix> find();
	
}
