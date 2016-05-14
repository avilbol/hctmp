package com.hallocasa.services.user.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.test.ChildrenTestEntity;
import com.hallocasa.dataentities.app.test.TestEntity;
import com.hallocasa.services.UserServicesImpl;
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
	
	/**
	 * 
	 * @return
	 */
	private UserVO createGoodParameters() {
		UserVO userVO = new UserVO();
		userVO.setEmail("alxvilbol@gmail.com");
		userVO.setLanguage(Language.en);
		CountryVO country = new CountryVO();
		StateVO state = new StateVO();
		CityVO city = new CityVO();
		MultiLanguageText countryName = new MultiLanguageText();
		countryName.setLangValue(Language.es, "Colombia");
		MultiLanguageText stateName = new MultiLanguageText();
		stateName.setLangValue(Language.es, "Bogota");
		MultiLanguageText cityName = new MultiLanguageText();
		cityName.setLangValue(Language.es, "Bogota");
		MultiLanguageText userTypeName = new MultiLanguageText();
		userTypeName.setLangValue(Language.es, "Traductor");
		MultiLanguageText userTypeTooltip = new MultiLanguageText();
		userTypeTooltip.setLangValue(Language.es, "Quien traduce");
		MultiLanguageText description = new MultiLanguageText();
		description.setLangValue(Language.de, "Hallo Ich Bin Alei");
		UserTypeVO usertype = new UserTypeVO();
		usertype.setManageCertificate(Boolean.TRUE);
		usertype.setManageTooltip(Boolean.FALSE);
		usertype.setUserTypeName(userTypeName);
		usertype.setUserTypeTooltip(userTypeTooltip);
		usertype.setId(4l);
		country.setDefaultLanguage(Language.es);
		country.setCountryIndicative("");
		country.setId(1l);
		country.setCode("");
		country.setCountryName(countryName);
		state.setCountry(country);
		state.setId(1l);
		state.setStateName(stateName);
		city.setState(state);
		city.setId(1l);
		city.setCityName(cityName);
		userVO.setLanguage(Language.de);
		userVO.setPassword(CodecUtils.encryptPassword("Kkkkkkkk"));
		userVO.setState(state);
		userVO.setConfirmedFlag(Boolean.TRUE);
		userVO.setId(1l);
		userVO.setLinkedIn("");
		userVO.setCountry(country);
		userVO.setState(state);
		userVO.setCity(city);
		userVO.setSpokenLanguages(Arrays.asList(new Language[] { Language.de }));
		userVO.setUserTypes(Arrays.asList(new UserTypeVO[] { usertype }));
		userVO.setFirstName("Alexander");
		userVO.setLastName("Villamil");
		userVO.setSkype("superskype");
		userVO.setWebSite("");
		userVO.setMainSpokenLanguage(Language.de);
		userVO.setUserDescription(description);
		return userVO;
	}

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
		UserVO user = new UserVO();
		user.setId(1l);
		propertyServices.setAppPersistenceServices(persistenceServices);
		List<PropertyVO> properties = propertyServices.find(user);
		PropertyVO propertyVO = properties.get(0);
		Assert.assertEquals(propertyVO.getPropertyBasicInfo().getTitle()
				.getLangValue(Language.es),
				"Propiedad de prueba en Los Rosales");
	}
	
	
	@Test
	public void testParentAndChildren(){
		TestEntity t = new TestEntity(1l, "hi2");
		List<ChildrenTestEntity> cteList = new ArrayList<>();
		ChildrenTestEntity cte = new ChildrenTestEntity();
		//cte.getChildrenTestEntityPK().setPspEntId(2);
		//cte.getChildrenTestEntityPK().setSecPspEntId("kiwi");
		cte.setAttr("my attr2871969");
		cte.setParent(t);
		cte.getPspEnt().setId(7);
		cte.getSecPspEnt().setId("re");
		cteList.add(cte);
		t.setChildren(cteList);
		
		persistenceServices.loadTransaction().begin();
		persistenceServices.mergeEntity(t);
		persistenceServices.loadTransaction().commit();
	}
	
	@Test
	public void testLoadAndSaveUserWithProperties() throws IllegalAccessException,
			InvocationTargetException, ServiceException {
		UserVO userVO = createGoodParameters();	
		PropertyServicesImpl propertyServices = new PropertyServicesImpl();
		UserVO user = new UserVO();
		user.setId(1l);
		propertyServices.setAppPersistenceServices(persistenceServices);
		List<PropertyVO> properties = propertyServices.find(user);
		properties.get(0).getPropertyBasicInfo().setArea(new BigDecimal("194"));
		properties.get(0).setPropertyLocation(new PropertyLocationVO());
		properties.get(0).getPropertyLocation().setId(3);
		userVO.setProperties(properties);

		persistenceServices.loadTransaction().begin();
		UserServicesImpl userServices = new UserServicesImpl();
		userServices.setAppPersistenceServices(persistenceServices);
		userServices.save(userVO);
		persistenceServices.loadTransaction().commit();
	}
	
	@Test
	public void testLoadAndSaveUserWithNewProperty() throws IllegalAccessException,
			InvocationTargetException, ServiceException {
		UserServicesImpl userServices = new UserServicesImpl();
		userServices.setAppPersistenceServices(persistenceServices);
		UserVO userVO = userServices.find(1l);
		
		PropertyVO propertyVO = userVO.getProperties().get(0);
		userVO.getProperties().add(propertyVO);
		userVO.getProperties().get(1).setId(null);
		
		persistenceServices.loadTransaction().begin();
		userServices.save(userVO);
		persistenceServices.loadTransaction().commit();
	}

}
