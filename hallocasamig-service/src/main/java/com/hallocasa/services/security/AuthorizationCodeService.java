package com.hallocasa.services.security;

import java.util.Optional;

import com.hallocasa.vo.security.AuthorizationCode;

/**
 * Contract to class {@link AuthorizationCode}
 */
public interface AuthorizationCodeService {

	/**
	 * Search an authorization code with the supplied parameters
	 * @param clientId
	 * 		client id param
	 * @param authorizationCode
	 * 		authorization code param
	 * @return
	 * 		the authorization code that matches with supplied param, empty value otherwise
	 */
	public Optional<AuthorizationCode> find(String clientId, String authorizationCode);
	
	/**
	 * Generates new authorization code
	 * @param clientId
	 * 		The clientId of authorization code
	 * @return
	 * 		Authorization code generated
	 */
	public AuthorizationCode generate(String clientId);
	
}
