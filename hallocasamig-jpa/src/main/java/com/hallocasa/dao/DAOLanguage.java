package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOLanguage;
import com.hallocasa.entities.EntityLanguage;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.Language;

/**
 * DAO for class {@link Language}
 */
@Stateless
public class DAOLanguage implements IDAOLanguage {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityLanguage> find() {
		return appPersistenceServices.executeNamedQuery(EntityLanguage.QUERY_FIND_ALL, new Object[] {},
				EntityLanguage.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityLanguage> findSystem() {
		return appPersistenceServices.executeNamedQuery(
				EntityLanguage.QUERY_FIND_SYSTEM_LANG, new Object[] {},
				EntityLanguage.class);
	}

}
