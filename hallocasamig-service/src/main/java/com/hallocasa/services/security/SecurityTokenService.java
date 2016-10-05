package com.hallocasa.services.security;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.vo.security.SecurityToken;

/**
 * Contract of class {@link SecurityToken}
 */
public interface SecurityTokenService {

	/**
	 * Validates supplied token 
	 * @param tokenValue
	 * 		token to validate
	 * @throws SecurityException
	 * 		if validation fails
	 */
	void validate(String tokenValue) throws SecurityException;
	
	/**
	 * Creates new token and assign a time of expiration
	 * @return
	 * 		The token generated
	 * @throws OAuthSystemException
	 * 		If there are problems when generating token
	 */
	SecurityToken generate() throws OAuthSystemException;
}
