/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

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

import com.hallocasa.commons.StrategySort;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.utils.FormatUtils;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;
import com.hallocasa.dataentities.wcm.TemporalPublisherUser;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.helpers.PropertyVOParser;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.constants.ServiceErrorMessage;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.vo.MailChimpMergeVars.TypeEnum;

/**
 * 
 * @author David Mantilla
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServicesImpl extends ServicesBase implements UserServices {

	/* constances */
	@SuppressWarnings("unused")
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
	public UserVO findBasicInfo(String email) {
		List<User> users = appPersistenceServices.executeNamedQuery(
				User.QUERY_FIND_BASIC_BY_EMAIL, new Object[] { email }, User.class);
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
		if(userVO.getProperties() != null){
			buildProperties(userVO, user);
		}
		appPersistenceServices.mergeEntity(user);
	}

	private void buildProperties(UserVO userVO, User user) {
		for (PropertyVO property : userVO.getProperties()) 
			if (property.getId() == null) property.setId(generatePropertyId());
		user.setProperties(PropertyVOParser.getInstance()
				.toEntity(userVO.getProperties()));
		for (Property property : user.getProperties()) property.setUser(user);
	}

	/**
	 * Method to generate a unique random property Id
	 * @return
	 */
	public String generatePropertyId(){
		Integer ocur;
		String idCandidate = "";
		do{
			idCandidate = FormatUtils.randomStrId();
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("1", idCandidate);
			ocur = appPersistenceServices.executeQuery(
					Property.QUERY_FIND_COUNT_BY_ID, paramMap, Integer.class, 1);
		} while(ocur != null);
		return idCandidate;
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
	public Integer loadUserVOCount() {
		return appPersistenceServices.executeQuery(
				User.QUERY_COUNT_LIST_WITH_USER_TYPES, Long.class).intValue();
	}

	@Override
	public List<UserVO> loadUserVOList(Integer initialAmmount,
			StrategySort strategySort) {
		return createUserVOList(null, initialAmmount);
	}

	@Override
	public List<UserVO> loadUserVOList(List<UserVO> existingUserVOList,
			Integer aditionalAmmount, StrategySort strategySort) {
		return createUserVOList(existingUserVOList, aditionalAmmount);
	}

	/**
	 * Detect the presence of users with an id duplicated in candidate new
	 * element
	 * 
	 * @param user
	 * @param userList
	 * @return
	 */
	private boolean duplicateUser(User user, List<User> userList) {
		for (User userItem : userList) {
			if (user.getId().equals(userItem.getId())) {
				return true;
			}
		}
		return false;
	}

	private List<UserVO> toUserVOList(List<User> userList) {
		List<UserVO> userVOList = new ArrayList<UserVO>();
		int counter = 0;
		for (User user : userList) {
			userVOList.add(ParsersContext.USER_VO_PARSER.toValueObject(
					userList.get(counter++), UserVO.class));
		}
		return userVOList;
	}

	private List<User> toUserList(List<UserVO> userVOList) {
		List<User> userList = new ArrayList<User>();
		for (UserVO userVO : userVOList) {
			userList.add(ParsersContext.USER_VO_PARSER.toEntity(userVO,
					User.class));
		}
		return userList;
	}

	private List<UserVO> createUserVOList(List<UserVO> existingUserVOList,
			Integer elementNumber) {
		Integer counter = 0;
		List<User> userList;
		if (existingUserVOList == null) {
			userList = new ArrayList<User>();
		} else {
			userList = toUserList(existingUserVOList);
		}
		Integer profileAmmount = appPersistenceServices.executeQuery(
				User.QUERY_COUNT_LIST_WITH_USER_TYPES, Long.class).intValue();
		Random random = new Random();
		while (counter++ < elementNumber && profileAmmount > userList.size()) {
			User userItem = new User();
			do {
				Integer indexToFix = random.nextInt(profileAmmount);
				userItem = appPersistenceServices.executeQuery(
						User.QUERY_ALL_LIST_WITH_USER_TYPES,
						new HashMap<String, Object>(), User.class, indexToFix);
			} while (duplicateUser(userItem, userList));
			userList.add(userItem);
		}
		return toUserVOList(userList);
	}

	public void setAppPersistenceServices(
			AppPersistenceServices appPersistenceServices) {
		this.appPersistenceServices = appPersistenceServices;
	}
}
