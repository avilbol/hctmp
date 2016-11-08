package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOExample;
import com.hallocasa.entities.EntityExample;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

@Stateless
public class DAOExample implements IDAOExample {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public EntityExample findByCode(Integer code) {
		List<EntityExample> entityExampleList = appPersistenceServices.executeNamedQuery(
                EntityExample.QUERY_FIND_BY_IDENTIFIER, new Object[]{code}, EntityExample.class);
		if(!entityExampleList.isEmpty()){
			return entityExampleList.get(0);
		}
		return null;
	}

	@Override
	public boolean saveExample(EntityExample entityExample) {
		appPersistenceServices.mergeEntity(entityExample);
		return true;
	}

}
