package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityNeighborhood;
import com.hallocasa.vo.Neighborhood;

/**
 * DAO Contract for class {@link Neighborhood}
 */
public interface IDAONeighborhood {

	/**
	 * Find neighborhoods with the specified city id
	 * @param cityId
	 * 		The city id that neighborhoods must have
	 * @return
	 * 		Neighborhoods that have the specified city id
	 */
	List<EntityNeighborhood> findByCityId(Integer cityId);
	
	/**
	 * Find neighborhood list with generic purpose
	 * @return
	 * 		Neighborhood list with generic purpose
	 */
	List<EntityNeighborhood> findGenericUse();

	/**
	 * Find neighborhoods with the specified city ids
	 * @param cityId
	 * 		The city identifiers that neighborhoods must have
	 * @return
	 * 		Neighborhoods that have any of the specified city ids
	 */
	List<EntityNeighborhood> findByCityIds(List<Integer> cityIds);
	
}
