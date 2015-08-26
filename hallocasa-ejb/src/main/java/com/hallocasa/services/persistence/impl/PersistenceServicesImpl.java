/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.persistence.impl;

import com.hallocasa.services.persistence.local.PersistenceServices;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Session Bean implementation class PartnerServices
 */
/**
 * PersistenceServices implementation
 *
 * @author David Mantilla
 * @since 1.7
 *
 */
@Stateless
public class PersistenceServicesImpl implements PersistenceServices {

    @PersistenceContext(unitName = "RealStateDatabasePU")
    private EntityManager em;
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(PersistenceServicesImpl.class.getName());

    /* Constructor */
    /**
     * Constructor with dependencies
     *
     * @param em
     */
    protected PersistenceServicesImpl(EntityManager em) {
        this.em = em;
        initialize();
    }

    /**
     * Default Constructor
     */
    public PersistenceServicesImpl() {
    }

    /**
     * Initialize
     */
    @PostConstruct
    final public void initialize() {
        //
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
    public <T> T persistEntity(T entity )
            throws PersistenceException {
        em.persist(entity);
        return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#removeEntity
     * (java.lang.Object)
     */
    @Override
    public <T> void removeEntity(T entity )
            throws PersistenceException {
        em.remove(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#mergeEntity
     * (java.lang.Object)
     */
    @Override
    public <T> T mergeEntity(T entity)
            throws PersistenceException {
        em.merge(entity);
        return entity;
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
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeNamedQuery
     * (java.lang.String, java.util.HashMap)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<?> executeNamedQuery(String sentence,
            HashMap<String, Object> params) {

        Query query = em.createNamedQuery(sentence);

        if (params != null && !params.isEmpty()) {

            Iterator<Map.Entry<String, Object>> it = params.entrySet()
                    .iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> paramEntry = it.next();
                query.setParameter(String.valueOf(paramEntry.getKey()),
                        paramEntry.getValue());
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeNamedQuery
     * (java.lang.String, java.lang.Object[], java.lang.Class, int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> executeNamedQuery(String queryName, Object[] params,
            Class<T> expectedClass, Integer startIndex, Integer endIndex) {
        Query query = em.createNamedQuery(queryName);

        // set limits
        if (startIndex == null) {
            startIndex = 0;
        }
        query.setFirstResult(startIndex);
        if (endIndex != null) {
            query.setMaxResults(endIndex - startIndex);
        }

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
     * @see com.mobiera.social.services.local.PersistenceServicesLocal#
     * executeCriteriaQuery(javax.persistence.criteria.CriteriaQuery,
     * java.lang.Class, int, int)
     */
    @Override
    public <T> List<T> executeCriteriaQuery(CriteriaQuery<T> criteriaQuery,
            Integer startIndex, Integer endIndex) {
        TypedQuery<T> query = em.createQuery(criteriaQuery);

        // set limits
        if (startIndex == null) {
            startIndex = 0;
        }
        query.setFirstResult(startIndex);
        if (endIndex != null) {
            endIndex += 1;
            query.setMaxResults((endIndex - startIndex));
        }

        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeQuery
     * (java.lang.String, java.lang.Object[], java.lang.Class)
     */
    @Override
    public <T> List<T> executeQuery(String jpqlQuery, Object[] params,
            Class<T> expectedClass, Integer startIndex, Integer endIndex) {

        TypedQuery<T> query = em.createQuery(jpqlQuery, expectedClass);

        // set limits
        if (startIndex == null) {
            startIndex = 0;
        }
        query.setFirstResult(startIndex);
        if (endIndex != null) {
            query.setMaxResults(endIndex - startIndex);
        }

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
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeQuery
     * (java.lang.String, java.util.HashMap, java.lang.Class, java.lang.Integer,
     * java.lang.Integer)
     */
    @Override
    public <T> List<T> executeQuery(String jpqlQuery,
            HashMap<String, Object> params, Class<T> expectedClass,
            Integer startIndex, Integer endIndex) {

        TypedQuery<T> query = em.createQuery(jpqlQuery, expectedClass);

        // set limits
        if (startIndex == null) {
            startIndex = 0;
        }
        query.setFirstResult(startIndex);
        if (endIndex != null) {
            query.setMaxResults(endIndex - startIndex);
        }

        if (params != null && !params.isEmpty()) {

            Iterator<Map.Entry<String, Object>> it = params.entrySet()
                    .iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> paramEntry = it.next();
                query.setParameter(String.valueOf(paramEntry.getKey()),
                        paramEntry.getValue());
            }
        }

        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeQuery
     * (java.lang.String, java.util.HashMap, java.lang.Class)
     */
    @Override
    public <T> List<T> executeQuery(String jpqlQuery,
            HashMap<String, Object> params, Class<T> expectedClass) {

        TypedQuery<T> query = em.createQuery(jpqlQuery, expectedClass);

        if (params != null && !params.isEmpty()) {

            Iterator<Map.Entry<String, Object>> it = params.entrySet()
                    .iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> paramEntry = it.next();
                query.setParameter(String.valueOf(paramEntry.getKey()),
                        paramEntry.getValue());
            }
        }

        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeUpdate
     * (java.lang.String, java.util.HashMap)
     */
    @Override
    public int executeUpdate(String sentence, HashMap<String, Object> params ) {

        Query query = em.createQuery(sentence);

        if (params != null && !params.isEmpty()) {

            Iterator<Map.Entry<String, Object>> it = params.entrySet()
                    .iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> paramEntry = it.next();
                query.setParameter(String.valueOf(paramEntry.getKey()),
                        paramEntry.getValue());
            }
        }

        return query.executeUpdate();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#executeUpdate
     * (java.lang.String, java.lang.Object[])
     */
    @Override
    public int executeUpdate(String sentence, Object[] params) {
        Query query = em.createQuery(sentence);

        int i = 1;
        if (params != null) {
            for (Object entry : params) {
                query.setParameter(i, entry);
                i++;
            }
        }

        return query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.services.local.PersistenceServicesLocal#
     * executeNativeUpdate(java.lang.String, java.lang.Object[])
     */
    @Override
    public int executeNativeUpdate(String sentence, Object[] params ) {
        Query query = em.createNativeQuery(sentence);

        int i = 1;
        if (params != null) {
            for (Object entry : params) {
                query.setParameter(i, entry);
                i++;
            }
        }

        return query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mobiera.social.services.local.PersistenceServicesLocal#getEntityManager
     * ()
     */
    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}