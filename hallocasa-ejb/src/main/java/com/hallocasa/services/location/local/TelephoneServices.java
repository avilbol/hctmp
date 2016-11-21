package com.hallocasa.services.location.local;

import java.util.List;

import com.hallocasa.commons.vo.CountryTelephonePrefix;

/**
* Interface to define methods in order to 
* manage telephone data and prefixes
*/
public interface TelephoneServices {

	/**
	 * @return the list of country prefixes available
	 */
	List<CountryTelephonePrefix> getCountryPrefixList();
	
}
