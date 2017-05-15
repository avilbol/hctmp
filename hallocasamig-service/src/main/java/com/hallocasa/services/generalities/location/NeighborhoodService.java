package com.hallocasa.services.generalities.location;

import java.util.List;

import com.hallocasa.vo.Neighborhood;

/**
 * Service for neighborhoods
 */
public interface NeighborhoodService {

	/**
	 * Find the neighborhoods with the specified city id
	 * @param cityId
	 * 		the city id
	 * @return
	 * 		the neighborhoods that match the specified city id, or
	 * 		generic neighborhood options somewhere else
	 */
	public List<Neighborhood> findByCityId(Integer cityId);
	
	
}
