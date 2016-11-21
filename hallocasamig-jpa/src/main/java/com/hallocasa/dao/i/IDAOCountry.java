package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityCountry;
import com.hallocasa.vo.Country;

/**
 * DAO Contract for class {@link Country}
 */
public interface IDAOCountry {

	/**
	 * Find the countries supported by system
	 * @return
	 * 		The countries supported by system
	 */
	public List<EntityCountry> find();
	
}
