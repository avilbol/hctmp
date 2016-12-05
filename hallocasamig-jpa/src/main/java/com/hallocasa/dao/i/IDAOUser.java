package com.hallocasa.dao.i;

import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.hallocasa.entities.EntityUser;


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
}
