/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.impl;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.StrategySort;
import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.commons.vo.StateVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.TestEntity;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.test.UserTest;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.UserServicesImpl;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.services.persistence.impl.AppPersistenceServicesImpl;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.tests.database.DatabaseCreator;
import com.hallocasa.tests.database.DatabaseUtils;
import com.hallocasa.tests.database.JhonDoeDataFiller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Alexander Villamil
 */
public class ProfileUserIT {

	private static final String METHOD_NAME = "registerUser";
	/* static fields */
	private static final Logger LOG = Logger.getLogger(RegisterUserTest.class
			.getName());
	private static final Locale EN = new Locale("en");

	/* instance variables */
	private AppPersistenceServices persistenceServices;
	private UserServices userServices;
	private EntityManagerFactory emf;
	private EntityManager em;

	/* constructors */
	@Before
	public void initialize() {
		Locale.setDefault(EN);

		// Open persistence unit
		try {
			emf = DatabaseUtils.loadTestAppPersistenceUnit();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "", e);
			throw new RuntimeException(e);
		}
		em = emf.createEntityManager();

		// creates database
		// DatabaseCreator databaseBuilder = new DatabaseCreator(emf);
		// databaseBuilder.addDatabaseFiller(new JhonDoeDataFiller());
		// databaseBuilder.fillDatabase();

		// services
		persistenceServices = new AppPersistenceServicesImpl(em);
		userServices = new UserServicesImpl();
	}

	private MailChimpServices mailChimpServices;

	/* Methods */
	@Test
	public void testSaveProfile() {
		UserVO userVO = createGoodParameters();
		MultiLanguageText mt = new MultiLanguageText();
		mt.setLangValue(Language.de, "stift");
		userVO.getState().setStateName(mt);
		userVO.getState().setCountry(userVO.getCountry());
		UserTest user = ParsersContext.USER_TEST_VO_PARSER.toEntity(userVO,
				UserTest.class);
		user.getCountry().setJavaCode("");
		user.setPassword(CodecUtils.encryptPassword("eeeeeeeeeeeeee"));
		persistenceServices.loadTransaction().begin();
		persistenceServices.mergeEntity(user);
		persistenceServices.flush();
		persistenceServices.loadTransaction().commit();
	}

	/* Methods */
	@Test
	public void testSaveEntity() {
		Long idToSearch = 1l;
		String urlToMatch = "new image url";
		TestEntity entity = persistenceServices.findEntity(TestEntity.class,
				idToSearch);
		entity.setImageUrl(new ImageContainer(urlToMatch));
		entity.setOtherAttribute("value 1 modified");
		persistenceServices.loadTransaction().begin();
		persistenceServices.mergeEntity(entity);
		persistenceServices.loadTransaction().commit();
		Assert.assertEquals(
				persistenceServices.findEntity(TestEntity.class, idToSearch)
						.getImageUrl().getUrl(), urlToMatch);
	}

	/* Methods */
	@Test
	public void testLoadProfileList() {
		UserServicesImpl userServices = new UserServicesImpl();
		userServices.setAppPersistenceServices(persistenceServices);
		List<UserVO> userVOList = userServices.loadUserVOList(5,
				StrategySort.RANDOM);
		List<UserVO> plusUserVOList = userServices.loadUserVOList(userVOList,
				5, StrategySort.RANDOM);
		List<UserVO> fullUserVOList = userServices.loadUserVOList(plusUserVOList,
				5, StrategySort.RANDOM);
	}

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
		userVO.setUserDescription(description);
		return userVO;
	}

}