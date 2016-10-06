package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;

import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.utils.constants.exceptions.ServiceException;
import com.hallocasa.utils.strategies.StrategySort;

public class DAOUser implements IDAOUser {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public EntityUser find(String email) {
		List<EntityUser> users = appPersistenceServices.executeNamedQuery(
				EntityUser.QUERY_FIND_BY_EMAIL, new Object[] { email }, EntityUser.class);
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public EntityUser find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(EntityUser user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer loadEntityUserVOCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityUser> loadEntityUserVOList(Integer initialAmmount, StrategySort strategySort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityUser> loadEntityUserVOList(List<EntityUser> existingEntityUserVOList, Integer aditionalAmmount,
			StrategySort strategySort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityUser> createEntityUserVOList(List<EntityUser> existingEntityUserVOList, Integer elementNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
