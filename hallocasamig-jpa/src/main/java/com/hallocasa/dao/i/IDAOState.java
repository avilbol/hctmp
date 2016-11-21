package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityState;
import com.hallocasa.vo.State;

/**
 * DAO Contract for class {@link State}
 */
public interface IDAOState {

	/**
	 * Find states with the specified country id
	 * @param countryId
	 * 		The country id that states must have
	 * @return
	 * 		Stated that have the specified id
	 */
	List<EntityState> findByCountryId(Integer countryId);
	
	/**
	 * Find states with the specified country id list
	 * @param countryIdList
	 * 		The country id list where states that match at least one of them
	 * @return
	 * 		States that match any element of the country id list
	 */
	List<EntityState> findByCountriesId(List<Integer> countryIdList);
}
