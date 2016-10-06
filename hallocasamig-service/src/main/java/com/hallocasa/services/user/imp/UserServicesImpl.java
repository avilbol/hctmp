/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.imp;

import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.services.user.UserServices;
import com.hallocasa.utils.constants.exceptions.ServiceException;
import com.hallocasa.utils.strategies.StrategySort;
import com.hallocasa.vo.User;

/**
 * 
 * @author David Mantilla
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServicesImpl implements UserServices {

	/* constants */
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(UserServicesImpl.class
			.getName());

	@EJB
	private IDAOUser daoUser;
	
	
	@Override
	public User find(String email) {
		return (User) toValueObject(daoUser.find(email));
	}
	@Override
	public User find(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> loadUserList(Integer initialAmmount, StrategySort strategySort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> loadUserList(List<User> existingUserList, Integer aditionalAmmount, StrategySort strategySort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save(User userVO) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer loadUserCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
