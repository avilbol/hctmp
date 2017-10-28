package com.hallocasa.dao.i;

import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.hallocasa.entities.EntityUser;
import com.hallocasa.vo.resultrequest.ResultRequest;


/**
 * Contract for class {@link EntityUser}
 * 
 * @author avillamil
 */
@Local
public interface IDAOUser {

	Optional<EntityUser> find(String email);

	Optional<EntityUser> find(Long id);

	void save(EntityUser user);

	Long loadEntityShowableUserCount();

	Long fetchRandomUserId(Long userCount, List<Long> excludeIdList);
	
	List<EntityUser> loadUserListByIdList(List<Long> idList);

	void updatePassword(Long userId, String newPassword);

	/**
	 * Load count of showable of user excluding specified ids
	 * @param excludeIdList
	 * 		Id list to exclude
	 * @return
	 * 		Count of showable users excluded specified list
	 */
	Long loadEntityShowableUserCount(List<Long> excludeIdList);

	List<Long> findIdentifierListByFilterRequest(String filterQuery, ResultRequest resultRequest);

	Long findIdentifierCountByFilterRequest(String filterQuery);

	List<EntityUser> findByUserIdList(List<Long> idList, ResultRequest resultRequest);
}
