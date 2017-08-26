package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOPreferredSetting;
import com.hallocasa.entities.EntityPreferredSetting;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * DAO implementation for access to preferred settings
 * @author Alexander
 */
@Stateless
public class DAOPreferredSetting implements IDAOPreferredSetting {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public List<EntityPreferredSetting> find() {
		return appPersistenceServices.executeNamedQuery(EntityPreferredSetting.QUERY_FIND, new Object[] {},
				EntityPreferredSetting.class);
	}
}
