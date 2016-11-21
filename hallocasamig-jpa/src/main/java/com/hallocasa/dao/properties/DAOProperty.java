package com.hallocasa.dao.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.hcfilter.PropertyFilterRequest;

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
	public List<EntityProperty> findBasic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityProperty> findBasic(PropertyFilterRequest request) {
		// TODO Auto-generated method stub
		return null;
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

}
