package com.hallocasa.dao.i.security;

import java.util.Optional;

import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.vo.security.AuthorizationCode;

/**
 * Contract for DAO of class {@link AuthorizationCode}
 */
public interface IDAOAuthorizationCode {

	/**
	 * Search an authorization code that matches supplied filters
	 * @param clientId
	 * 		Filter by client id. If null, it does not apply
	 * @param authCode
	 * 		Filter by authorization code. If null, it does not apply
	 * @return
	 * 		The authorization code filtered, empty otherwise
	 */
	Optional<EntityAuthorizationCode> find(String clientId, String authCode);
	
	/**
	 * Persist an authorization code
	 * @param authorizationCode
	 * 		The authorization code to persist
	 * @return
	 * 		true if successful operation, false if problems happen
	 */
	boolean save(EntityAuthorizationCode authorizationCode);
	
}
