package com.hallocasa.services.properties.impl;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hallocasa.commons.utils.FormatUtils;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.helpers.PropertyVOParser;
import com.hallocasa.helpers.UserVOParser;
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

	private UserVOParser userVOParser = ParsersContext.USER_VO_PARSER;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyVO> find(UserVO userVO) {
		User user = userVOParser.toEntity(userVO, User.class);
		List<Property> entityList = appPersistenceServices.executeNamedQuery(Property.QUERY_FIND_BY_USER_ID,
				new Object[] { user }, Property.class);
		return PropertyVOParser.getInstance().toValueObject(entityList);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyVO findByPropertyId(String id) {
		List<Property> entityList = appPersistenceServices.executeNamedQuery(Property.QUERY_FIND_BY_ID,
				new Object[] { id }, Property.class);
		List<PropertyVO> voList = PropertyVOParser.getInstance().toValueObject(entityList);
		if(voList != null && !voList.isEmpty()){
			return voList.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(PropertyVO propertyVO) {
		Property property = new Property();
		property.setId(propertyVO.getId());
		Object[] params = new Object[] { property };
		appPersistenceServices.executeNamedQuery(Property.QUERY_DELETE_BY_ID, new Object[] { property.getId() });
		appPersistenceServices.executeNamedQuery(PropertyFieldValue.QUERY_DELETE_BY_PROPERTY_ID, params);
		return true;
	}

	@Override
	public void add(PropertyVO propertyVO) {
		User user = userVOParser.toEntity(propertyVO.getUser(), User.class);
		Property property = PropertyVOParser.getInstance().toEntity(propertyVO);
		property.setId(this.generatePropertyId(property.getId()));
		property.setUser(user);
		appPersistenceServices.mergeEntity(property);
	}

	@Override
	public void save(PropertyVO propertyVO) {
		User user = userVOParser.toEntity(propertyVO.getUser(), User.class);
		Property property = PropertyVOParser.getInstance().toEntity(propertyVO);
		property.setUser(user);
		appPersistenceServices.mergeEntity(property);
	}

	@Override
	public String generatePropertyId() {
		return generatePropertyId(null);
	}

	/**
	 * Method to generate a unique random property Id
	 * 
	 * @return
	 */
	@Override
	public String generatePropertyId(String candidate) {
		Integer ocur;
		String idCandidate = "";
		do {
			idCandidate = (candidate == null ? FormatUtils.randomStrId() : candidate);
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("1", idCandidate);
			ocur = appPersistenceServices.executeQuery(Property.QUERY_FIND_COUNT_BY_ID, paramMap, Integer.class, 1);
		} while (ocur != null);
		return idCandidate;
	}

	public AppPersistenceServices getAppPersistenceServices() {
		return appPersistenceServices;
	}

	public void setAppPersistenceServices(AppPersistenceServices appPersistenceServices) {
		this.appPersistenceServices = appPersistenceServices;
	}



	@Override
	public List<PropertyVO> find(PropertyFilter propertyFilter) {
		// TODO Auto-generated method stub
		return null;
	}
}
