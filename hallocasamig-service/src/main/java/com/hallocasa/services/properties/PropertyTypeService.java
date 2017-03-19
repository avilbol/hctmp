package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyType;
import com.hallocasa.vo.hcfilter.properties.dto.PropertyTypeGroupDTO;

/**
 * Contract service for class {@link PropertyType}
 * 
 * @author avillamil
 */
public interface PropertyTypeService {

	/**
	 * Find all property types available on system
	 */
	public List<PropertyType> find();

	/**
	 * Find all property type groups available on system
	 */
	public List<PropertyTypeGroupDTO> findGroups();

}
