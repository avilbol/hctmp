package com.hallocasa.dao.i.properties;

import java.util.List;

import com.hallocasa.entities.properties.EntityPropertyLocation;

public interface IDAOPropertyLocation {

	/**
	 * Find all property locations available on system
	 */
	public List<EntityPropertyLocation> find();
	
}
