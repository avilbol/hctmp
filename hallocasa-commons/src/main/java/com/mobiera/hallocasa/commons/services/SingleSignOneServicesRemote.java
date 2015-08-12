package com.mobiera.hallocasa.commons.services;

import javax.ejb.Remote;

import com.mobiera.hallocasa.commons.exceptions.services.ExpiredTokenException;
import com.mobiera.hallocasa.commons.exceptions.services.InactiveUserException;
import com.mobiera.hallocasa.commons.exceptions.services.InvalidIpLoginException;
import com.mobiera.hallocasa.commons.exceptions.services.InvalidTokenException;
import com.mobiera.hallocasa.commons.vo.TokenVO;
import com.mobiera.hallocasa.commons.vo.TokenValidationInfoVO;

/**
 * Services related to single sign on process
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Remote
public interface SingleSignOneServicesRemote {
	/* static fields */

	/* Methods */

	/**
	 * Validates token and if is valid returns all associated information
	 * 
	 * @param tokenCode
	 * @param ip
	 * @param toAppId
	 * @return Token validation info value object
	 * @throws InvalidTokenException When the token doesn't exist in database or
	 *             the requested application doesn't match token's appTo
	 * @throws ExpiredTokenException When the token is already expired
	 * @throws InactiveUserException When the user is inactive
	 * @throws InvalidIpLoginException When the IP is not allowed in the white
	 *             list
	 */
	public TokenValidationInfoVO validateToken(String tokenCode, String ip,
		long toAppId) throws InvalidTokenException, ExpiredTokenException,
		InactiveUserException, InvalidIpLoginException;

	/**
	 * Request a new token for navigate between applications
	 * 
	 * @param accountId Id of the account who is navigating
	 * @param fromAppId Id of the application the user come from
	 * @param toAppId If of the application the user come to
	 * @param url URL where the user will be redirected when the token is
	 *            validated
	 * @return The created token value object
	 */
	public TokenVO requestToken(long accountId, long fromAppId, long toAppId,
		String url);

	/* Getters & Setters */

}
