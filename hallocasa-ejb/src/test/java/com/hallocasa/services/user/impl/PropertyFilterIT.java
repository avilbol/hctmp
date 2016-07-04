package com.hallocasa.services.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyFieldVO;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFieldFilter;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.dataentities.app.properties.PropertyField;
import com.hallocasa.services.UserServicesImpl;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.properties.impl.PropertyFilteringServicesImpl;
import com.hallocasa.services.properties.impl.PropertyServicesImpl;
import com.hallocasa.tests.database.DatabaseUtils;

public class PropertyFilterIT {

	
	
	
private AppPersistenceServices persistenceServices;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	

	/* constructors */
	@Before
	public void initialize() {

		// Open persistence unit
		try {
			emf = DatabaseUtils.loadTestAppPersistenceUnit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		em = emf.createEntityManager();

		// services
		persistenceServices = new AppPersistenceServicesImpl(em);
	}
	
	public PropertyFilter basicFilter(){
		PropertyFilter filter = new PropertyFilter();
		PropertyTypeVO ptype = new PropertyTypeVO();
		ptype.setId(2);
		PropertyTypeVO ptype2 = new PropertyTypeVO();
		ptype2.setId(2);
		filter.setPropertyTypeList(Arrays.asList(new PropertyTypeVO[]{ptype,ptype2}));
		PropertyLocationVO plocation1 = new PropertyLocationVO();
		plocation1.setId(1);
		filter.setPropertyLocationList(Arrays.asList(new PropertyLocationVO[]{plocation1}));
		return filter;
	}
	
	public PropertyFilter fullFilter(){
		PropertyFilter filter = basicFilter();
	    PropertyFieldFilter fieldFilter1 = new PropertyFieldFilter();
	    PropertyFieldVO field = new PropertyFieldVO();
	    field.setId(1);
	    fieldFilter1.setPropertyField(field);
	    List<PropertyFieldFilter> filters = new ArrayList<PropertyFieldFilter>();
	    filters.add(fieldFilter1);
	    
	    PropertyFieldFilter fieldFilter2 = new PropertyFieldFilter();
	    field = new PropertyFieldVO();
	    field.setId(2);
	    fieldFilter2.setPropertyField(field);
	    filters.add(fieldFilter2);
	    
	    filter.setPropertyFieldFilters(filters);
	    return filter;
	}
	
	@Test
	public void testGetIdsFromKeys(){
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		psimpl.loadIdsForFiltering(basicFilter());
	}
	
	
	@Test
	public void testGetPropertyFieldValueFromIds(){
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		psimpl.loadPropertyFieldEligible(psimpl.loadIdsForFiltering(basicFilter()), 
				fullFilter().getPropertyFieldFilters());
	}
	

	
}
