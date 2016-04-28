package com.hallocasa.services.user.impl;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.regex.Matcher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.hallocasa.commons.StrategySort;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.services.UserServicesImpl;
import com.hallocasa.services.interfaces.PropertyServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.properties.impl.PropertyServicesImpl;
import com.hallocasa.tests.database.DatabaseUtils;

/**
 * Integration tests for property environment
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
	public void testLoadProfileList() {
		PropertyServicesImpl propertyServices = new PropertyServicesImpl();
		User user = new User();
		user.setId(1l);
		propertyServices.setAppPersistenceServices(persistenceServices);
		List<Property> properties = propertyServices.find(user);
		Assert.assertThat(properties.size(), CoreMatchers.is(1));
	}
	
}
