package com.hallocasa.dao;

import java.util.HashMap;
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

@Stateless
public class DAOUser implements IDAOUser {

	@EJB
	private AppPersistenceServices appPersistenceServices;

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

	@Override
	public List<EntityUser> loadUserListByIdList(List<Long> idList) {
		Object[] params = new Object[]{idList};
		List<EntityUser> userList = appPersistenceServices.executeNamedQuery(
				EntityUser.QUERY_FIND_BY_ID_LIST, params, EntityUser.class);
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
		return userList;
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
