package com.hallocasa.services.generalities.location;

import java.util.List;

import com.hallocasa.vo.State;

public interface StateService {

	/**
	 * Find the states with the specified country id
	 * @param countryId
	 * 		the country id
	 * @return
	 * 		the states that match the specified country id
	 */
	public List<State> findByCountryId(Integer countryId);
	
	/**
	 * Find the states which its id is in the specified country id list
	 * @param countryIdList
	 * 		the country id list
	 * @return
	 * 		state list with country id in specified list
	 */
	public List<State> findByCountriesId(List<Integer> countryIdList);
}
