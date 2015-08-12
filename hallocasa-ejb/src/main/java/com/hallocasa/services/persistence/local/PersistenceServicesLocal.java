/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.persistence.local;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author david
 */
public interface PersistenceServicesLocal {

    /**
     * @param <T> Entity Type
     * @param entity Entity to persist
     * @param accountId Id of the account who is executing action
     * @return the merged entity
     * @throws PersistenceException when merging fails
     */
    public <T> T mergeEntity(T entity, long accountId)
            throws PersistenceException;

    /**
     * Removes an entity
     *
     * @param <T>
     * @param entity
     * @param accountId Id of the account who is executing action
     * @throws PersistenceException
     */
    public <T> void removeEntity(T entity, long accountId)
            throws PersistenceException;

    /**
     * @param <T> Entity Type
     * @param entity Entity to persist
     * @param accountId Id of the account who is executing action
     * @return the persisted entity
     * @throws PersistenceException when persisting fails
     */
    public <T> T persistEntity(T entity, long accountId)
            throws PersistenceException;

    /**
     * @param <T> Entity Type
     * @param entityClass
     * @param primaryKey
     * @return the found entity
     * @throws PersistenceException
     */
    public <T> T findEntity(Class<T> entityClass, Object primaryKey)
            throws PersistenceException;

    /**
     * @param <T> expected Type
     * @param queryName Name of the named query
     * @param params array with parameters in the order the query expects. If no
     * parameters are needed the value can be null
     * @param expectedClass expected class
     * @return query result
     */
    public <T> List<T> executeNamedQuery(String queryName, Object[] params,
            Class<T> expectedClass);

    /**
     * @param queryName
     * @param params
     * @param expectedClass
     * @return the query result
     */
    public <T> List<T> executeNamedQuery(String queryName,
            HashMap<String, Object> params);

    /**
     * @param <T> expected Type
     * @param jpqlQuery Sentence of the query in JPQL syntax
     * @param params array with parameters in the order the query expects. If no
     * parameters are needed the value can be null
     * @param expectedClass expected class
     * @param startIndex Start index in the result. Send null if want to get
     * from start
     * @param endIndex End index in the query result. Send null if want all
     * records
     * @return query result
     */
    public <T> List<T> executeQuery(String jpqlQuery, Object[] params,
            Class<T> expectedClass, Integer startIndex, Integer endIndex);

    /**
     * @param jpqlQuery
     * @param params
     * @param expectedClass
     * @return the query result
     */
    public <T> List<T> executeQuery(String jpqlQuery,
            HashMap<String, Object> params, Class<T> expectedClass);

    /**
     * @param jpqlQuery
     * @param params
     * @param expectedClass
     * @param startIndex
     * @param endIndex
     * @return the result list of the query
     */
    public <T> List<T> executeQuery(String jpqlQuery,
            HashMap<String, Object> params, Class<T> expectedClass,
            Integer startIndex, Integer endIndex);

    /**
     * @param <T> expected Type
     * @param queryName Name of the named query
     * @param params array with parameters in the order the query expects. If no
     * parameters are needed the value can be null
     * @param expectedClass expected class
     * @param startIndex Start index in the result. Send null if want to get
     * from start
     * @param endIndex End index in the query result. Send null if want all
     * records
     * @return query result
     */
    public <T> List<T> executeNamedQuery(String queryName, Object[] params,
            Class<T> expectedClass, Integer startIndex, Integer endIndex);

    /**
     * @param <T> Entity class
     * @param criteriaQuery
     * @param startIndex Start index in the result. Send null if want to get
     * from start
     * @param endIndex End index in the query result. Send null if want all
     * records
     * @return The query result
     */
    public <T> List<T> executeCriteriaQuery(CriteriaQuery<T> criteriaQuery,
            Integer startIndex, Integer endIndex);

    /**
     * @param sentence Query expression
     * @param params array with parameters in the order the query expects. If no
     * parameters are needed the value can be null
     * @return query result
     */
    public List<Object> executeNativeQuery(String sentence, Object[] params);

    /**
     * @param sentence
     * @param params
     * @param accountId Id of the account who is executing action
     * @return the update process result
     */
    public int executeUpdate(String sentence, HashMap<String, Object> params,
            long accountId);

    /**
     * Execute an updates query with enumerated parameters
     *
     * @param sentence
     * @param params
     * @param accountId Id of the account who is executing action
     * @return the update process result
     */
    public int executeUpdate(String sentence, Object[] params, long accountId);

    /**
     * @return Entity Manager
     */
    public EntityManager getEntityManager();

    /**
     * Executes native query
     *
     * @param sentence
     * @param params
     * @param accountId Id of the account who is executing action
     * @return query execution result
     */
    public int executeNativeUpdate(String sentence, Object[] params,
            long accountId);
}
