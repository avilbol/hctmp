package com.hallocasa.dao.i.properties;

import java.util.List;

import com.hallocasa.entities.properties.EntityPropertyProposal;

public interface IDAOPropertyProposal {

	/**
	 * Find all property proposals available on system
	 */
	public List<EntityPropertyProposal> find();
	
}
