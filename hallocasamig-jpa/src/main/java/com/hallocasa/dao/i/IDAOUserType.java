package com.hallocasa.dao.i;

import java.util.List;

import com.hallocasa.entities.EntityUserType;

public interface IDAOUserType {

	/**
	 * Return the user type list available on system
	 */
	public List<EntityUserType> find();
	
}
