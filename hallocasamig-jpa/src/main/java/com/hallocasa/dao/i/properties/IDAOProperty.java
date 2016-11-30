package com.hallocasa.dao.i.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.entities.properties.EntityPropertyFieldValue;

public interface IDAOProperty {

	/**
	 * Update (or create) a property in the system
	 * @param property
	 * 		The property to update or create
	 */
	void save(EntityProperty property);
	
	/**
	 * Delete the property with the specified id
	 * @param propertyId
	 * 		Id of property to delete
	 */
	void delete(String propertyId);
	
	/**
	 * Find properties which its id is within the list supplied
	 * @param idList
	 * 		Identifier list supplied
	 * @param orderBy
	 * 		String list attributes that apply in order by
	 * @param asc
	 * 		Specify if the attributes sort ascending (true) or descending (false)
	 * @return
	 * 		Properties matching with any of identifiers
	 */
	List<EntityProperty> findByPropertyIdList(List<String> idList, List<String> orderBy, boolean asc);
	
	/**
	 * Find properties which its id is within the list supplied
	 * @param idList
	 * 		Identifier list supplied
	 * @return
	 * 		Properties matching with any of identifiers
	 */
	List<EntityPropertyFieldValue> findValuesByPropertyIdList(List<String> idList);
	
	/**
	 * Find the basic properties
	 * @param resultsNumber
	 * @return
	 * 		The basic properties (?)
	 */
	List<EntityProperty> findBasicRandom(Integer resultsNumber);
	
	/**
	 * Execute a specific filter query, returning the property identifier matching result list
	 * @param filterQuery
	 * @param params
	 * @param pageInfo
	 * @return
	 */
	List<String> findIdentifierListByFilterRequest(String filterQuery, HashMap<String, Object> params, 
			Integer... pageInfo);
	
	/**
	 * Find the basic property identifiers corresponding to specified user
	 * @param userId
	 * 		The user id which the properties allow
	 * @return
	 * 		The properties of that user
	 */
	List<String> findByUser(Integer userId);
	
	/**
	 * Find a detailed property by its id
	 * @param id
	 * @return
	 */
	Optional<EntityProperty> findById(String id);
}
