package com.hallocasa.services.user.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.helpers.PropertyVOParser;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.properties.impl.PropertyServicesImpl;
import com.hallocasa.tests.database.DatabaseUtils;

/**
 * Integration tests for property environment
 * 
 * @author Alexander Villamil
 */
public class PropertyIT {

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
	public void testLoadProfileList() throws IllegalAccessException,
			InvocationTargetException {
		PropertyServicesImpl propertyServices = new PropertyServicesImpl();
		User user = new User();
		user.setId(1l);
		propertyServices.setAppPersistenceServices(persistenceServices);
		List<PropertyVO> properties = propertyServices.find(user);
		PropertyVO propertyVO = properties.get(0);
		Assert.assertEquals(propertyVO.getPropertyBasicInfo().getTitle()
				.getLangValue(Language.es),
				"Propiedad de prueba en Los Rosales");
	}

}
