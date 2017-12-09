package com.hallocasa.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.EntityUserDescription;
import com.hallocasa.entities.EntityUserLanguage;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.jpaservices.i.QueryUtils;
import com.hallocasa.vo.resultrequest.ResultRequest;

@Stateless
public class DAOUser implements IDAOUser {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@EJB
	private QueryUtils queryUtils;

	@Override
	public Optional<EntityUser> find(String email) {
		return appPersistenceServices.executeSingleNamedQuery(EntityUser.QUERY_FIND_BASIC_BY_EMAIL,
				new Object[] { email }, EntityUser.class);
	}

	@Override
	public Optional<EntityUser> find(Long id) {
		return appPersistenceServices.executeSingleNamedQuery(
				EntityUser.QUERY_FIND_BY_ID, new Object[]{id}, EntityUser.class);
	}

	@Override
	public void save(EntityUser user) {
		appPersistenceServices.mergeEntity(user);
	}
	
	@Override
	public void updatePassword(Long userId, String newPassword) {
		appPersistenceServices.executeNamedQuery(EntityUser.QUERY_UPDATE_PASSWORD, 
				new Object[]{newPassword, userId});
	}

	@Override
	public Long loadEntityShowableUserCount() {
		return appPersistenceServices.executeQuery(
				EntityUser.QUERY_COUNT_LIST_WITH_USER_TYPES, 
				new Object[]{}, Long.class).get(0);
	}
	
	@Override
	public Long loadEntityShowableUserCount(List<Long> excludeIdList) {
		HashMap<String, Object> params = new HashMap<>();
		StringBuilder query = new StringBuilder(EntityUser.QUERY_COUNT_LIST_WITH_USER_TYPES);
		if(excludeIdList != null && !excludeIdList.isEmpty()){
			query.append(" AND u.id NOT IN ?1 ");
			params.put("1", excludeIdList);
		}
		return appPersistenceServices.executeQuery(
				query.toString(), params, Long.class).get(0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> findIdentifierListByFilterRequest(String filterQuery,
			ResultRequest resultRequest) {
		StringBuilder resultQuery = new StringBuilder("");
		resultQuery.append(filterQuery)
				.append(queryUtils.loadOrderBySnippetQuery(resultRequest.getOrderBy(), resultRequest.getAsc()));
		List<Object> objList = appPersistenceServices.executeNativeQuery(resultQuery.toString(), null,
				resultRequest.getPageFrom() - 1, resultRequest.getPageTo());
		List<Long> resultList = new LinkedList<>();
		for (Object obj : objList) {
			resultList.add(Long.parseLong(String.valueOf(obj)));
		}
		return resultList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long findIdentifierCountByFilterRequest(String filterQuery) {
		return (Long) (appPersistenceServices.executeNativeQuery(filterQuery, new Object[0]).get(0));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityUser> findByUserIdList(List<Long> idList, ResultRequest resultRequest) {
		if(idList == null || idList.isEmpty()){
			return new LinkedList<EntityUser>();
		}
		String query = "select u from EntityUser u where u.id IN ?1 ";
		StringBuilder resultQuery = new StringBuilder("");
		resultQuery.append(query);
		if (resultRequest != null) {
			resultQuery.append(queryUtils.loadOrderBySnippetQuery(resultRequest.getOrderBy(), resultRequest.getAsc()));
		}
		List<EntityUser> userList = appPersistenceServices.executeQuery(resultQuery.toString(), new Object[] { idList },
				EntityUser.class);
		complement(idList, userList);
		return userList;
	}

	@Override
	public List<EntityUser> loadUserListByIdList(List<Long> idList) {
		Object[] params = new Object[]{idList};
		List<EntityUser> userList = appPersistenceServices.executeNamedQuery(
				EntityUser.QUERY_FIND_BY_ID_LIST, params, EntityUser.class);
		complement(idList, userList);
		return userList;
	}
	
	private void complement(List<Long> idList, List<EntityUser> userList){
		Object[] params = new Object[]{idList};
		List<EntityUserLanguage> userLangList = appPersistenceServices.executeNamedQuery(
				EntityUserLanguage.QUERY_FIND_BY_ID_LIST, params, 
				EntityUserLanguage.class);
		List<EntityUserDescription> userDescList = appPersistenceServices.executeNamedQuery(
				EntityUserDescription.QUERY_FIND_BY_ID_LIST, params, 
				EntityUserDescription.class);
		HashMap<Long, EntityUser> userMap = new HashMap<>();
		for(EntityUser entUser : userList){
			userMap.put(entUser.getId(), entUser);
		}
		for(EntityUserLanguage entUserLang : userLangList){
			userMap.get(entUserLang.getUser().getId()).getUserLanguages().add(entUserLang);
		}
		for(EntityUserDescription entUserDesc : userDescList){
			userMap.get(entUserDesc.getUser().getId())
				.getUserDescriptions().add(entUserDesc);
		}
	}

	@Override
	public Long fetchRandomUserId(Long userCount, List<Long> excludeIdList) {
		Integer indexToFix = new Random().nextInt(userCount.intValue());
		HashMap<String, Object> params = new HashMap<>();
		StringBuilder query = new StringBuilder(EntityUser.QUERY_ID_LIST_WITH_USER_TYPES);
		if(excludeIdList != null && !excludeIdList.isEmpty()){
			query.append(" AND u.id NOT IN ?1 ");
			params.put("1", excludeIdList);
		}
		return appPersistenceServices.executeQuery(query.toString(), params, Long.class, indexToFix);
	}
}
