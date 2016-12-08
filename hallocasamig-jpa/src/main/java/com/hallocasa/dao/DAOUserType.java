package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOUserType;
import com.hallocasa.entities.EntityUserType;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

@Stateless
public class DAOUserType implements IDAOUserType {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityUserType> find() {
		return appPersistenceServices.executeNamedQuery(EntityUserType.QUERY_FIND_ALL, 
				new Object[]{}, EntityUserType.class);
	}

}
