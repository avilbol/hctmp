package com.hallocasa.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.hallocasa.dao.i.IDAOCurrencyExchangeData;
import com.hallocasa.dateutils.DateUtils;
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
	public void clean() {
		appPersistenceServices.executeNativeUpdate(
				EntityCurrencyExchangeData.NATIVE_QUERY_DELETE_OLD, new Object[] {});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTodayUpdated() {
		try{
			Date mostRecentDate = appPersistenceServices.executeQuery(
					EntityCurrencyExchangeData.QUERY_LAST_UPDATE, Date.class);
			return mostRecentDate != null && DateUtils.isToday(mostRecentDate);
		} catch(NoResultException e){
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Double> findRate(Integer currencyFromId, Integer currencyToId) {
		return appPersistenceServices.executeSingleNamedQuery(EntityCurrencyExchangeData.QUERY_FIND_RATE_EXCHANGE, 
				new Object[] {currencyFromId, currencyToId}, Double.class);
	}
}
