package com.hallocasa.services.interfaces;

import java.util.List;

import javax.persistence.PersistenceException;

public interface PersistenceServicesInterface {
	/**
	 * @param <T>
	 *            Entity Type
	 * @param entity
	 *            Entity to persist
	 * @return the merged entity
	 * @throws PersistenceException
	 *             when merging fails
	 */
	public <T> T mergeEntity(T entity) throws PersistenceException;

	/**
	 * @param <T>
	 *            Entity Type
	 * @param entity
	 *            Entity to persist
	 * @return the persisted entity
	 * @throws PersistenceException
	 *             when persisting fails
	 */
	public <T> T persistEntity(T entity) throws PersistenceException;

	/**
	 * @param <T>
	 *            Entity Type
	 * @param entityClass
	 * @param primaryKey
	 * @return the found entity
	 * @throws PersistenceException
	 */
	public <T> T findEntity(Class<T> entityClass, Object primaryKey)
			throws PersistenceException;

	/**
	 * @param <T>
	 *            expected Type
	 * @param queryName
	 *            Name of the named query
	 * @param params
	 *            array with parameters in the order the query expects. If no
	 *            parameters are needed the value can be null
	 * @param expectedClass
	 *            expected class
	 * @return query result
	 */
	public <T> List<T> executeNamedQuery(String queryName, Object[] params,
			Class<T> expectedClass);

	/**
	 * @param sentence
	 *            Query expression
	 * @param params
	 *            array with parameters in the order the query expects. If no
	 *            parameters are needed the value can be null
	 * @return query result
	 */
	public List<Object> executeNativeQuery(String sentence, Object[] params);

}
