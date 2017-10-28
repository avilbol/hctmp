/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user;

import java.util.List;

import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.vo.User;
import com.hallocasa.vo.dto.UserListRequest;
import com.hallocasa.vo.hcfilter.UserFilterRequest;
import com.hallocasa.vo.hcfilter.UserFilterResult;

/**
 * Service class for users of system hallocasa
 */
public interface UserService {

	/**
	 * Finds a user by email
	 * 
	 * @param email
	 * @return
	 */
	User find(String email);

	/**
	 * Finds a user by email
	 * 
	 * @param email
	 * @return
	 */
	List<User> addUsersToShowableList(UserListRequest userListRequest);
	
	/**
	 * Finds a user by its id
	 * 
	 * @param id
	 * @return
	 */
	User find(long id);

	/**
	 * 
	 * @param user
	 */
	void save(User user, String token);

	/**
	 * 
	 * @param user
	 */
	void register(User user, String urlBase);

	/**
	 * Load the number of user offering services
	 * @return
	 */
	Integer loadUserCount();

	/**
	 * Validate if email already exists
	 * @param email
	 * 		The email to validate
	 */
	void validate(String email);

	/**
	 * Send a activation email to user who register
	 * @param email
	 * @param activationLink
	 * @param activationKey
	 * @throws MailServicesErrorException
	 */
	void sendActivationLinkEmail(User user, String activationLink, String activationKey)
			throws MailServicesErrorException;

	/**
	 * Activate an user that comes from email activation link
	 * @param email
	 * @param activationToken
	 */
	void activateUser(String email, String activationToken);

	/**
	 * Ends the user session by deleting its security access token
	 * @param userTokenTokenContent
	 * 		Content of user token to delete
	 */
	void logout(String userTokenTokenContent);

	/**
	 * filter users by specific request filter
	 * @param request
	 * @return
	 */
	UserFilterResult find(UserFilterRequest request);
}
