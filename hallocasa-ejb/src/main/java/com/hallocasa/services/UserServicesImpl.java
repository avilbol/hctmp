/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.StrategySort;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.RollbackException;
import javax.transaction.UserTransaction;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.junit.Assert;
import org.junit.Test;

import com.hallocasa.dataentities.wcm.TemporalPublisherUser;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.constants.ServiceErrorMessage;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.TestEntity;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.vo.MailChimpMergeVars.TypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;

/**
 * 
 * @author David Mantilla
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServicesImpl extends ServicesBase implements UserServices {

	/* constances */
	private final static Logger LOG = Logger.getLogger(UserServicesImpl.class
			.getName());

	/* Dependences */
	@PersistenceContext(unitName = "RealStateDatabasePU")
	private EntityManager em;
	@EJB
	private AppPersistenceServices appPersistenceServices;
	@Resource
	private SessionContext sessionContext;
	@EJB
	private MailChimpServices mailChimpServices;

	/* Methods */
	@Override
	public UserVO find(String email) {
		List<User> users = appPersistenceServices.executeNamedQuery(
				User.QUERY_FIND_BY_EMAIL, new Object[] { email }, User.class);
		if (users.isEmpty()) {
			return null;
		}
		UserVO userVO = ParsersContext.USER_VO_PARSER.toValueObject(
				users.get(0), UserVO.class);
		return userVO;
	}

	@Override
	public UserVO find(long id) {
		User user = appPersistenceServices
				.findEntityWithRefresh(User.class, id);
		if (user == null) {
			return null;
		}
		UserVO userVO = ParsersContext.USER_VO_PARSER.toValueObject(user,
				UserVO.class);
		return userVO;
	}

	@Override
	public void save(UserVO userVO) throws ServiceException {
		User user = ParsersContext.USER_VO_PARSER.toEntity(userVO, User.class);
		appPersistenceServices.mergeEntity(user);
		appPersistenceServices.flush();
	}

	/* Methods */
	@Override
	public void testSaveEntity() {
		Long idToSearch = 1l;
		String urlToMatch = "new image url";
		TestEntity entity = appPersistenceServices.findEntity(TestEntity.class,
				idToSearch);
		entity.setImageUrl(new ImageContainer(urlToMatch));
		entity.setOtherAttribute("value 1 modified");
		appPersistenceServices.mergeEntity(entity);
		Assert.assertEquals(
				appPersistenceServices.findEntity(TestEntity.class, idToSearch)
						.getImageUrl().getUrl(), urlToMatch);
	}

	/**
	 * 
	 * @throws ServiceException
	 */
	@Deprecated
	@Override
	public void savePropertyPublisher(TemporalPublisherUser publisherUser)
			throws ServiceException {
		// TODO: change this transaction management. Let container manages
		// transaction

		// gets the user transaction
		UserTransaction txn = sessionContext.getUserTransaction();

		// begins transaction
		beginTransaction(txn);

		try {
			em.persist(publisherUser);
			mailChimpServices.subscribeNewUser(publisherUser.getEmail(),
					publisherUser.getFullName(), "",
					publisherUser.getLanguage(), TypeEnum.PUBLISHER);
			txn.commit();
		} catch (RollbackException re) {
			DatabaseException de = unwrapDatabaseException(re);
			if (de == null) {
				throw new RuntimeException(re.getLocalizedMessage());
			}
			if (de.getErrorCode() == ServiceErrorMessage.MYSQL_DUPLICATED_ENTRY_ERROR_CODE) {
				throw new ServiceException(
						"Email already exists in our database",
						ServiceErrorMessage.USER_SERVICES_EMAIL_EXISTS_ERROR,
						de);
			}
		} catch (Exception e) {
			rollbackTransaction(txn);
			throw new ServiceException("Unexpected Error",
					ServiceErrorMessage.COMMON_UNEXPECTED_ERROR, e);
		}
	}

	@Override
	public List<UserVO> loadUserVOList(Integer initialAmmount,
			StrategySort strategySort) {
		List<UserVO> userVOList = appPersistenceServices.executeQuery(
				User.QUERY_FIND_RANDOM_LIST, new Object[] {}, UserVO.class, 0,
				initialAmmount);
		return userVOList;
	}

	@Override
	public List<UserVO> loadUserVOList(List<UserVO> existingUserVOList,
			Integer aditionalAmmount, StrategySort strategySort) {
		List<Long> exclList = new ArrayList<Long>();
		for (UserVO userVO : existingUserVOList) {
			exclList.add(userVO.getId());
		}
		HashMap<String, Object> objectMap = new HashMap<String, Object>();
		objectMap.put("exclList", exclList);
		List<UserVO> users = appPersistenceServices.executeQuery(
				User.QUERY_FIND_RANDOM_EXCLUDE_LIST, objectMap, UserVO.class,
				0, aditionalAmmount);
		existingUserVOList.addAll(users);
		return existingUserVOList;
	}

}
