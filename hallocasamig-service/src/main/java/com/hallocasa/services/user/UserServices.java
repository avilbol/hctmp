/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user;

import java.util.List;

import com.hallocasa.utils.constants.exceptions.ServiceException;
import com.hallocasa.utils.strategies.StrategySort;
import com.hallocasa.vo.User;



/**
 * Service class for users of system hallocasa
 */
public interface UserServices {

	/**
	 * Finds a user by email
	 * 
	 * @param email
	 * @return
	 */
	User find(String email);

	/**
	 * Finds a user by its id
	 * 
	 * @param id
	 * @return
	 */
	User find(long id);

	/**
	 * Find a user list
	 * @param initialAmmount
	 * 		Number of elements to return
	 * @return
	 */
	List<User> loadUserList(Integer initialAmmount, StrategySort strategySort);

	/**
	 * Find a concatenation between an existing User list and a new
	 * generated randomly User element list
	 * @param existingUserList
	 * @param aditionalAmmount
	 * @return
	 */
	List<User> loadUserList(List<User> existingUserList,
			Integer aditionalAmmount, StrategySort strategySort);

	/**
	 * 
	 * @param user
	 */
	void save(User userVO) throws ServiceException;


	/**
	 * Load the number of user offering services
	 * @return
	 */
	Integer loadUserCount();
}
