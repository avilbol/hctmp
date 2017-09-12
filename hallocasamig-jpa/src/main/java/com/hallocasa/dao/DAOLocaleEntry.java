package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOLocaleEntry;
import com.hallocasa.entities.EntityLocaleEntry;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * Data access object for crud locale entry definitions
 * @author avilbol
 */
@Stateless
public class DAOLocaleEntry implements IDAOLocaleEntry {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public List<EntityLocaleEntry> find() {
		return appPersistenceServices.executeNamedQuery(EntityLocaleEntry.QUERY_FIND_ALL, new Object[] {},
				EntityLocaleEntry.class);
	}

	@Override
	public List<EntityLocaleEntry> find(String locale) {
		return appPersistenceServices.executeNamedQuery(EntityLocaleEntry.QUERY_FIND_BY_LOCALE, 
				new Object[] {locale},
				EntityLocaleEntry.class);
	}

	@Override
	public void save(List<EntityLocaleEntry> localeEntryList) {
		appPersistenceServices.mergeEntityList(localeEntryList);
	}

	@Override
	public void delete(String pnemonic) {
		appPersistenceServices.executeNamedQuery(EntityLocaleEntry.QUERY_DELETE_BY_PNEMONIC_ITEM, 
				new Object[] {pnemonic});
	}

	@Override
	public List<EntityLocaleEntry> findByPnemonic(String pnemonic) {
		return appPersistenceServices.executeNamedQuery(EntityLocaleEntry.QUERY_FIND_BY_PNEMONIC, 
				new Object[] {pnemonic},
				EntityLocaleEntry.class);
	}
}
