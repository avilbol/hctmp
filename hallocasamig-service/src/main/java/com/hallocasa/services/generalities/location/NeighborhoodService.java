package com.hallocasa.services.generalities.location;

import java.util.List;

import com.hallocasa.services.generalities.Listable;
import com.hallocasa.vo.Neighborhood;

/**
 * Service contract for neighborhoods
 */
public interface NeighborhoodService extends Listable<Neighborhood> {

	/**
	 * Find the neighborhoods with the specified city id
	 * @param cityId
	 * 		the city id
	 * @return
	 * 		the neighborhoods that match the specified city id, or
	 * 		generic neighborhood options somewhere else
	 */
	public List<Neighborhood> findByCityId(Integer cityId);

	/**
	 * Find the neighborhoods with the specified city identifiers
	 * @param cityIds
	 * 		the city identifiers to search in match
	 * @return
	 * 		the neighborhoods that match any of the specified city identifiers, 
	 * 		or generic neighborhood options somewhere else
	 */
	public List<Neighborhood> findByCityIds(List<Integer> cityIds);
	
	
}
