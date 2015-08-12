package com.mobiera.hallocasa.commons.services;

import javax.ejb.Remote;

import com.mobiera.hallocasa.commons.exceptions.services.InactiveUserException;
import com.mobiera.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.mobiera.hallocasa.commons.exceptions.services.InvalidIpLoginException;
import com.mobiera.hallocasa.commons.exceptions.services.InvalidPasswordLoginException;
import com.mobiera.hallocasa.commons.exceptions.services.NoAppAccessException;
import com.mobiera.hallocasa.commons.vo.AccountVO;
import com.mobiera.hallocasa.commons.vo.AppAccessInfoVO;
import com.mobiera.hallocasa.commons.vo.AppAuthInfoVO;
import com.mobiera.hallocasa.commons.vo.CredentialVO;
import com.mobiera.hallocasa.commons.vo.QueryResult;
import com.mobiera.hallocasa.commons.vo.criteria.QueryCriteria;

/**
 * Services related to Accounts
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Remote
public interface AccountServicesRemote {

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	/**
	 * Finds the list of users who has access to appId, belongs to operationId's
	 * partner and that has access to profile with useCases
	 * 
	 * @param operationId Id of the operation for filtering by
	 * @param appId Id of the application for filtering by
	 * @param useCases Names of the use-cases for filtering by
	 * @param queryCriteria Object with index information for paging the result
	 * @return Found list of accounts matching the filtering attributes and
	 *         limited by the queryCriteria information
	 */
	public QueryResult<AccountVO> findAccounts(long operationId, long appId,
		String[] useCases, QueryCriteria queryCriteria);

	/**
	 * Validates credential and return the account info and the information of
	 * that account in the application
	 * 
	 * @param credentialVO Value object with credentials information
	 * @param appId Id of the application where the users wants to log in
	 * @return A value object with authentication information
	 * 
	 * @throws InvalidEmailException  When the email doesn't exist in the social database
	 * @throws InvalidPasswordLoginException  When the login doesn't not match
	 * @throws InactiveUserException  When the user is inactive
	 * @throws InvalidIpLoginException  When the IP is not allowed to authenticate
	 */
	public AppAuthInfoVO authenticate(CredentialVO credentialVO, long appId)
		throws InvalidEmailException, InvalidPasswordLoginException,
		InactiveUserException, InvalidIpLoginException;
	
	
	/**
	 * Return the application access information for the main account of the
	 * partner of the given operation
	 * 
	 * @param operationid
	 * @param appId
	 * @return An appAccessInfoVO object with all access information
	 * @throws NoAppAccessException
	 */
	public AppAccessInfoVO getSupervisedOperationAccessInfo(long operationid,
		long appId) throws NoAppAccessException;

	/* Getters & Setters */
}
