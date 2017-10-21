package com.hallocasa.services.generalities.location;

import java.util.List;

import com.hallocasa.services.generalities.Listable;
import com.hallocasa.vo.City;

public interface CityService extends Listable<City> {

	/**
	 * Find the cities with the specified state id
	 * @param stateId
	 * 		the state id
	 * @return
	 * 		the cities that match the specified state id
	 */
	public List<City> findByStateId(Integer stateId);
	
	/**
	 * Find the cities which its id is in the specified state id list
	 * @param stateIdList
	 * 		the state id list
	 * @return
	 * 		city list with state id in specified list
	 */
	public List<City> findByStatesId(List<Integer> stateIdList);
	
}
