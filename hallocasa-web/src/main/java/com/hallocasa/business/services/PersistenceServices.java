package com.hallocasa.business.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.hallocasa.business.services.interfaces.PersistenceServicesInterface;

/**
 * PersistenceServices implementation
 * 
 * @author David Mantilla
 * @since 1.7
 *
 */
@Stateless
public class PersistenceServices implements PersistenceServicesInterface {

	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

	/* Constructor */

	/**
	 * Constructor with dependencies
	 * @param em 
	 */
	public PersistenceServices(EntityManager em) {
		this.em = em;
	}

	/**
	 * Default Constructor
	 */
	public PersistenceServices() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiera.social.services.local.PersistenceServicesLocal#findEntity
	 * (java.lang.Class, java.lang.Object)
	 */
	@Override
	public <T> T findEntity(Class<T> entityClass, Object primaryKey)
			throws PersistenceException {
		return em.find(entityClass, primaryKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiera.social.services.local.PersistenceServicesLocal#persistEntity
	 * (java.lang.Object)
	 */
	@Override
	public <T> T persistEntity(T entity) throws PersistenceException {
		em.persist(entity);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiera.social.services.local.PersistenceServicesLocal#mergeEntity
	 * (java.lang.Object)
	 */
	@Override
	public <T> T mergeEntity(T entity) throws PersistenceException {
		throw new PersistenceException("I want to make rollback");
		// em.merge(entity);
		// return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiera.social.services.local.PersistenceServicesLocal#executeNamedQuery
	 * (java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executeNamedQuery(String queryName, Object[] params,
			Class<T> expectedClass) {
		Query query = em.createNamedQuery(queryName);
		int i = 1;
		if (params != null) {
			for (Object entry : params) {
				query.setParameter(i, entry);
				i++;
			}
		}
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiera.social.services.local.PersistenceServicesLocal#executeNativeQuery
	 * (java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeNativeQuery(String sentence, Object[] params) {
		Query query = em.createNativeQuery(sentence);
		int i = 1;
		if (params != null) {
			for (Object entry : params) {
				query.setParameter(i, entry);
				i++;
			}
		}
		return query.getResultList();
	}
}
