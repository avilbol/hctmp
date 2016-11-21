package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityCountryTelephonePrefix;
import com.hallocasa.vo.CountryTelephonePrefix;

/**
 * DAO Contract for class {@link CountryTelephonePrefix}
 */
public interface IDAOCountryTelephonePrefix {

	/**
	 * Find the country telephone prefix list supported by system
	 * @return
	 * 		The country telephone prefix list supported by system
	 */
	public List<EntityCountryTelephonePrefix> find();
	
}
