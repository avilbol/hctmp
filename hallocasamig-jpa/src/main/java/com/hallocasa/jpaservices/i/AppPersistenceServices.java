/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.jpaservices.i;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author david
 */
@Local
public interface AppPersistenceServices {

    /**
     * @param <T> Entity Type
     * @param entity Entity to persist
     * @return the merged entity
     * @throws PersistenceException when merging fails
     */
    <T> T mergeEntity(T entity)
            throws PersistenceException;

    /**
     * 
     * @return the transaction manager 
     */
    EntityTransaction loadTransaction();
    
    /**
     * Refresh data of entity
     * @param <T>
     * @param entity
     * @throws PersistenceException 
     */
    <T> void refresh(T entity) throws PersistenceException;
    
    /**
     * Removes an entity
     *
     * @param <T>
     * @param entity
     * @throws PersistenceException
     */
    <T> void removeEntity(T entity)
            throws PersistenceException;

    /**
     * @param <T> Entity Type
     * @param entity Entity to persist
     * @return the persisted entity
     * @throws PersistenceException when persisting fails
     */
    <T> T persistEntity(T entity)
            throws PersistenceException;

    /**
     * @param <T> Entity Type
     * @param entityClass
     * @param primaryKey
     * @return the found entity
     * @throws PersistenceException
     */
    <T> T findEntity(Class<T> entityClass, Object primaryKey)
            throws PersistenceException;
    
    /**
     * @param <T> Entity Type
     * @param entityClass
     * @param primaryKey
     * @return the found entity
     * @throws PersistenceException
     */
    <T> T findEntityWithRefresh(Class<T> entityClass, Object primaryKey)
            throws PersistenceException;
    
    

    /**
     * @param <T> expected Type
     * @param queryName Name of the named query
     * @param params array with parameters in the order the query expects. If no
     * parameters are needed the value can be null
     * @param expectedClass expected class
     * @return query result
     */
    <T> List<T> executeNamedQuery(String queryName, Object[] params,
            Class<T> expectedClass);

    /**
     * @param queryName
     * @param params
     * @return the query result
     */
    List<?> executeNamedQuery(String queryName,
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
    <T> List<T> executeQuery(String jpqlQuery, Object[] params,
            Class<T> expectedClass, Integer startIndex, Integer endIndex);

    /**
     * @param jpqlQuery
     * @param params
     * @param expectedClass
     * @return the query result
     */
    <T> List<T> executeQuery(String jpqlQuery,
            HashMap<String, Object> params, Class<T> expectedClass);

    /**
     * @param jpqlQuery
     * @param params
     * @param expectedClass
     * @param startIndex
     * @param endIndex
     * @return the result list of the query
     */
    <T> List<T> executeQuery(String jpqlQuery,
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
    <T> List<T> executeNamedQuery(String queryName, Object[] params,
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
    <T> List<T> executeCriteriaQuery(CriteriaQuery<T> criteriaQuery,
            Integer startIndex, Integer endIndex);

    /**
     * @param sentence Query expression
     * @param params array with parameters in the order the query expects. If no
     * parameters are needed the value can be null
     * @return query result
     */
    List<Object> executeNativeQuery(String sentence, Object[] params);

    /**
     * @param sentence
     * @param params
     * @return the update process result
     */
    int executeUpdate(String sentence, HashMap<String, Object> params);

    /**
     * Execute an updates query with enumerated parameters
     *
     * @param sentence
     * @param params
     * @return the update process result
     */
    int executeUpdate(String sentence, Object[] params);

    /**
     * 
     * @param queryName
     * @param params
     * @return 
     */
    int executeNamedQuery(String queryName, Object[] params);
    
    /**
     * @return Entity Manager
     */
    EntityManager getEntityManager();

    /**
     * Executes native query
     *
     * @param sentence
     * @param params
     * @return query execution result
     */
    int executeNativeUpdate(String sentence, Object[] params);

    /**
     * Flush entity manager
     */
    void flush();

    /**
     * 
     * @param sqlQuery
     * @param params
     * @param expectedClass
     * @param startIndex
     * @param endIndex
     * @return
     */
	List<Object> executeNativeQuery(String sqlQuery, HashMap<String, Object> params,
			 Integer startIndex, Integer endIndex);

	/**
	 * 
	 * @param sqlQuery
	 * @param expectedClass
	 * @return
	 */
	<T> T executeQuery(String sqlQuery, Class<T> expectedClass);

	<T> T executeQuery(String jpqlQuery, HashMap<String, Object> params,
			Class<T> expectedClass, Integer index);

	/**
	 * Returns an unique response value in query
	 * @param queryName
	 * @param params
	 * @param expectedClass
	 * @return
	 */
	<T> Optional<T> executeSingleNamedQuery(String queryName, Object[] params, Class<T> expectedClass);

	<T> List<T> executeQuery(String jpqlQuery, Object[] params, Class<T> expectedClass);

	/**
     * @param <T> Entity Type
     * @param entity Entity to persist
     * @return the merged entity
     * @throws PersistenceException when merging fails
     */
	<T> void mergeEntityList(List<T> entityList);

	/**
	 * Generates a named query
	 * @param queryName
	 * 		The name of query
	 * @param params
	 * 		Parameters that apply
	 * @return
	 */
	Query loadNamedQuery(String queryName, Object[] params);

	/**
	 * 
	 * @param sentence
	 * @param params
	 * @param expectedClass
	 * @return
	 */
	<T> List<T> executeNativeQuery(String sentence, Object[] params, Class<T> expectedClass);

	/**
	 * 
	 * @param sentence
	 * @param params
	 * @return
	 */
	List<Object> executeNativeQuery(String sentence, HashMap<String, Object> params);
}
