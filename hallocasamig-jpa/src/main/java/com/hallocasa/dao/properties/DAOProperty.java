package com.hallocasa.dao.properties;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.EntityCountry;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.entities.properties.EntityPropertyFieldValue;
import com.hallocasa.entities.properties.EntityPropertyLocation;
import com.hallocasa.entities.properties.EntityPropertyProposal;
import com.hallocasa.entities.properties.EntityPropertyType;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.jpaservices.i.QueryUtils;

/**
 * DAO for class {@link EntityProperty}
 * @author Alexander Villamil
 */
@Stateless
public class DAOProperty implements IDAOProperty {
	
	private static final String BASIC_PROPERTY_ATTR = "NEW EntityProperty(p.id, p.publishDate, p.propertyType, p.propertyLocation, "
			+ "p.propertyProposal, p.country)";
	
	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@EJB
	private QueryUtils queryUtils;
	
	@Override
	public void save(EntityProperty property) {
		appPersistenceServices.mergeEntity(property);
	}
	
	@Override
	public List<String> findByUser(Integer userId) {
		return appPersistenceServices.executeNamedQuery
				(EntityProperty.QUERY_FIND_ID_BY_USER_ID, new Object[]{userId}, String.class);
	}

	@Override
	public Optional<EntityProperty> findById(String id) {
		String query = EntityProperty.QUERY_FIND_BY_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(id);
		return appPersistenceServices.executeSingleNamedQuery
				(query, paramList.toArray(), EntityProperty.class);
	}

	@Override
	public void delete(String propertyId) {
		String query = EntityProperty.QUERY_DELETE_BY_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(propertyId);
		appPersistenceServices.executeNamedQuery(query, paramList.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityProperty> findByPropertyIdList(List<String> idList, List<String> orderBy, boolean asc) {
		String query = String.format("select %1$s from EntityProperty p where p.id IN ?1", BASIC_PROPERTY_ATTR);
		StringBuilder resultQuery = new StringBuilder("");
		resultQuery.append(query).append(queryUtils.loadOrderBySnippetQuery(orderBy, asc));
		return appPersistenceServices.executeQuery(resultQuery.toString(), 
				new Object[]{idList}, EntityProperty.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityPropertyFieldValue> findValuesByPropertyIdList(List<String> idList) {
		return appPersistenceServices.executeNamedQuery(EntityPropertyFieldValue.QUERY_FIND_BASIC_IN, 
				new Object[]{idList}, EntityPropertyFieldValue.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EntityProperty> findBasicRandom(Integer resultsNumber) {
		Integer propertyNmb = appPersistenceServices.executeSingleNamedQuery(EntityProperty.QUERY_COUNT, 
				new Object[]{}, Integer.class).get();
		Query query = appPersistenceServices.loadNamedQuery(EntityProperty.QUERY_FIND_BASIC, 
				new Object[]{});
		Integer rand = ThreadLocalRandom.current().nextInt(1, propertyNmb + 1);
		query.setFirstResult(rand).setMaxResults(resultsNumber);
        return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> findIdentifierListByFilterRequest(String filterQuery, HashMap<String, Object> paramMap,
			Integer... pageInfo) {
		List<Object> objList =  appPersistenceServices.executeNativeQuery(filterQuery, paramMap, 
				pageInfo[0], pageInfo[1]);
		List<String> resultList = new LinkedList<>();
		for(Object obj : objList){
			resultList.add((String) obj);
		}
		return resultList;
	}
}
