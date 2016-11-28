package com.hallocasa.dao.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.entities.properties.EntityPropertyFieldValue;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * DAO for class {@link EntityProperty}
 * @author Alexander Villamil
 */
@Stateless
public class DAOProperty implements IDAOProperty {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public void save(EntityProperty property) {
		appPersistenceServices.mergeEntity(property);
	}
	
	@Override
	public List<EntityProperty> findByUser(EntityUser entityUser) {
		// TODO Auto-generated method stub
		return null;
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
		String query = "select p from EntityProperty p where p.id IN ?1";
		return appPersistenceServices.executeNamedQuery(, 
				idList.toArray(), EntityProperty.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityPropertyFieldValue> findValuesByPropertyIdList(List<String> idList) {
		return appPersistenceServices.executeNamedQuery(EntityPropertyFieldValue.QUERY_FIND_BASIC_IN, 
				idList.toArray(), EntityPropertyFieldValue.class);
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
	public List<String> findIdentifierListByFilterRequest(String filterQuery, List<Object> params,
			Integer... pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}
}
