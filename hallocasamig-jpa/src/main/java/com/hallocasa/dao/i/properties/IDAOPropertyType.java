package com.hallocasa.dao.i.properties;

import java.util.List;

import com.hallocasa.entities.properties.EntityPropertyType;

public interface IDAOPropertyType {

	/**
	 * Find all property types available on system
	 */
	public List<EntityPropertyType> find();
	
}
