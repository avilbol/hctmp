package com.hallocasa.dao.i.security;

import java.util.Optional;

import com.hallocasa.entities.security.EntitySecurityToken;
import com.hallocasa.vo.security.SecurityToken;

/**
 * Contract for DAO of class {@link SecurityToken}
 */
public interface IDAOSecurityToken {

	/**
	 * Finds a regitered token with value specified
	 * @param tokenValue
	 * 		token value to search
	 * @return
	 * 		the token that matches the token value, empty if no match
	 */
	Optional<EntitySecurityToken> find(String tokenValue);
	
	/**
	 * Persist a token 
	 * @param token
	 * 		The token to persist
	 * @return
	 * 		true if succesful operation
	 */
	boolean save(EntitySecurityToken token);
}
