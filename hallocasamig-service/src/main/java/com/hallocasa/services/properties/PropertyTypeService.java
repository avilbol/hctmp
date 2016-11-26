package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyType;

/**
 * Contract service for class {@link PropertyType}
 * @author avillamil
 */
public interface PropertyTypeService {

	/**
	 * Find all property types available on system
	 */
	public List<PropertyType> find();
	
}
