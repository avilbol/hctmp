package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityCity;
import com.hallocasa.vo.City;

/**
 * DAO Contract for class {@link City}
 */
public interface IDAOCity {

	/**
	 * Find cities with the specified state id
	 * @param stateId
	 * 		The state id that cities must have
	 * @return
	 * 		Cities that have the specified id
	 */
	List<EntityCity> findByStateId(Integer stateId);
	
	/**
	 * Find cities with the specified state id list
	 * @param stateIdList
	 * 		The state id list where cities that match at least one of them
	 * @return
	 * 		Cities that match any element of the state id list
	 */
	List<EntityCity> findByStatesId(List<Integer> stateIdList);
}
