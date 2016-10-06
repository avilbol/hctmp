package com.hallocasa.dao.i;

import java.util.List;

import javax.ejb.Local;

import com.hallocasa.entities.EntityUser;
import com.hallocasa.utils.constants.exceptions.ServiceException;
import com.hallocasa.utils.strategies.StrategySort;


/**
 * Contract for class {@link EntityUser}
 * 
 * @author avillamil
 */
@Local
public interface IDAOUser {

	EntityUser find(String email);

	EntityUser find(long id);

	void save(EntityUser user) throws ServiceException;

	Integer loadEntityUserVOCount();

	List<EntityUser> loadEntityUserVOList(Integer initialAmmount, StrategySort strategySort);

	List<EntityUser> loadEntityUserVOList(List<EntityUser> existingEntityUserVOList, Integer aditionalAmmount, StrategySort strategySort);

	List<EntityUser> createEntityUserVOList(List<EntityUser> existingEntityUserVOList, Integer elementNumber);

}
