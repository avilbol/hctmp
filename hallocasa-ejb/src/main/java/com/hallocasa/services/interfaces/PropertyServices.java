package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;

/**
 * Interface for the service related with properties
 * @author Alexander Villamil
 */
public interface PropertyServices {

	/**
	 * Finds property list by user owner
	 * 
	 * @param user
	 * @return
	 */
	List<PropertyVO> find(UserVO user);
	
	/**
	 * Save property
	 * @param propertyVO
	 */
	void save(PropertyVO property);

	/**
	 * Add property
	 * @param propertyVO
	 */
	void add(PropertyVO propertyVO);

	/**
	 * @return
	 * 		unique id for set in a property entity
	 */
	String generatePropertyId();
	
	/**
	 * @return
	 * 		unique id for set in a property entity
	 * @param candidate
	 * 		test candidate first. If this is unique
	 * 		it will return it
	 */
	String generatePropertyId(String candidate);

	/**
	 * delete a property 
	 * @param propertyVO
	 * 		property to delete
	 * @return
	 * 		true if successful operation
	 */
	boolean delete(PropertyVO propertyVO);

	/**
	 * Find property filtering by its id
	 * @param id
	 * @return
	 * 		The property with id provided, null if not founf
	 */
	PropertyVO findByPropertyId(String id);
	
}
