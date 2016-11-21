package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCurrencyExchangeData;
import com.hallocasa.entities.EntityCountry;
import com.hallocasa.entities.EntityCurrencyExchangeData;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * DAO for class {@link EntityCurrencyExchangeData}
 */
@Stateless
public class DAOCurrencyExchangeData implements IDAOCurrencyExchangeData {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityCurrencyExchangeData> find() {
		return appPersistenceServices.executeNamedQuery(EntityCurrencyExchangeData.QUERY_FIND_ALL, new Object[] {},
				EntityCurrencyExchangeData.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(List<EntityCurrencyExchangeData> exchangeList) {
		appPersistenceServices.mergeEntityList(exchangeList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean clean() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTodayUpdated() {
		// TODO Auto-generated method stub
		return false;
	}
}
