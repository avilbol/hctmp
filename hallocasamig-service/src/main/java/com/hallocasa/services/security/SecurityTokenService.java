package com.hallocasa.services.security;

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
	 */
	SecurityToken generate();
}
