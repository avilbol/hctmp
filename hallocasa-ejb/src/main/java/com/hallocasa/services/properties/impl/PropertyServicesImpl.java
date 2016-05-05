package com.hallocasa.services.properties.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.helpers.PropertyVOParser;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;

/**
 * Service for management of properties
 * 
 * @author Alexander Villamil
 */
public class PropertyServicesImpl implements PropertyServices {

	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

	@EJB
	private AppPersistenceServices appPersistenceServices;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyVO> find(User user) {
		List<Property> entityList = appPersistenceServices.executeNamedQuery(
				Property.QUERY_FIND_BY_USER_ID, new Object[] { user },
				Property.class);
		return PropertyVOParser.getInstance().toValueObject(entityList);
	}

	public AppPersistenceServices getAppPersistenceServices() {
		return appPersistenceServices;
	}

	public void setAppPersistenceServices(
			AppPersistenceServices appPersistenceServices) {
		this.appPersistenceServices = appPersistenceServices;
	}
	
	
}
