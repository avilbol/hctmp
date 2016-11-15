package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.properties.PropertyField;

public interface PropertyFieldService {

	/**
	 * Returns the property fields within the type, proposal, location and country specified
	 * @param propertyKey
	 * @return
	 */
	public List<PropertyField> getPropertyFieldList(PropertyKey propertyKey);
	
	/**
	 * Returns all the property fields
	 * @param propertyKey
	 * @return
	 */
	public List<PropertyField> getPropertyFieldList();
}
