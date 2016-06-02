/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.StrategySort;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.wcm.TemporalPublisherUser;

/**
 * 
 * @author David Mantilla
 */
public interface UserServices {

	/**
	 * Finds a user by email
	 * 
	 * @param email
	 * @return
	 */
	public UserVO findBasicInfo(String email);

	/**
	 * Finds a user by its id
	 * 
	 * @param id
	 * @return
	 */
	public UserVO find(long id);

	/**
	 * Find a user list
	 * @param initialAmmount
	 * 		Number of elements to return
	 * @return
	 */
	public List<UserVO> loadUserVOList(Integer initialAmmount, StrategySort strategySort);

	/**
	 * Find a concatenation between an existing UserVO list and a new
	 * generated randomly UserVO element list
	 * @param existingUserVOList
	 * @param aditionalAmmount
	 * @return
	 */
	public List<UserVO> loadUserVOList(List<UserVO> existingUserVOList,
			Integer aditionalAmmount, StrategySort strategySort);

	/**
	 * 
	 * @param user
	 */
	public void save(UserVO userVO) throws ServiceException;

	/**
	 * 
	 * @param publisherUser
	 * @throws ServiceException
	 */
	public void savePropertyPublisher(TemporalPublisherUser publisherUser)
			throws ServiceException;

	/**
	 * Load the number of user offering services
	 * @return
	 */
	Integer loadUserVOCount();
}
