package com.hallocasa.services.properties.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.helpers.PropertyVOParser;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;

/**
 * Service for management of properties
 * 
 * @author Alexander Villamil
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PropertyServicesImpl implements PropertyServices {

	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;

	@EJB
	private AppPersistenceServices appPersistenceServices;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyVO> find(UserVO userVO) {
		User user = ParsersContext.USER_VO_PARSER.toEntity(userVO, User.class);
		List<Property> entityList = appPersistenceServices.executeNamedQuery(Property.QUERY_FIND_BY_USER_ID,
				new Object[] { user }, Property.class);
		return PropertyVOParser.getInstance().toValueObject(entityList);
	}

	@Override
	public void save(PropertyVO propertyVO) {
		User user = ParsersContext.USER_VO_PARSER.toEntity(propertyVO.getUser(), User.class);
		Property property = PropertyVOParser.getInstance().toEntity(propertyVO);
		property.setUser(user);
		appPersistenceServices.mergeEntity(user);
	}

	public AppPersistenceServices getAppPersistenceServices() {
		return appPersistenceServices;
	}

	public void setAppPersistenceServices(AppPersistenceServices appPersistenceServices) {
		this.appPersistenceServices = appPersistenceServices;
	}
}
