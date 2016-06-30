package com.hallocasa.services.user.impl;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
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
	
	@Test
	public void testGetIdsFromKeys(){
		PropertyServicesImpl psimpl = new PropertyServicesImpl();
		psimpl.setEm(em);
		PropertyFilter filter = new PropertyFilter();
		PropertyTypeVO ptype = new PropertyTypeVO();
		ptype.setId(1);
		PropertyTypeVO ptype2 = new PropertyTypeVO();
		ptype2.setId(2);
		filter.setPropertyTypeList(Arrays.asList(new PropertyTypeVO[]{ptype,ptype2}));
		PropertyLocationVO plocation1 = new PropertyLocationVO();
		plocation1.setId(2);
		filter.setPropertyLocationList(Arrays.asList(new PropertyLocationVO[]{plocation1}));
		psimpl.find(filter);
	}
	
	
	@Test
	public void testGetPropertyFieldValueFromIds(){
		PropertyFilteringServicesImpl psimpl = new PropertyFilteringServicesImpl();
		psimpl.setEm(em);
		psimpl.loadPropertyFieldValues(null);
	}
	
	
}
