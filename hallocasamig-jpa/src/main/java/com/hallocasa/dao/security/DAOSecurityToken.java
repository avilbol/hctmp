package com.hallocasa.dao.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.security.IDAOSecurityToken;
import com.hallocasa.entities.security.EntitySecurityToken;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.security.SecurityToken;

/**
 * DAO for class {@link SecurityToken}
 */
@Stateless
public class DAOSecurityToken implements IDAOSecurityToken {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<EntitySecurityToken> find(String tokenValue) {
		String query = EntitySecurityToken.QUERY_FIND_BY_TOKEN_VALUE;
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tokenValue);
		List<EntitySecurityToken> tokenList = appPersistenceServices.executeNamedQuery(query,
				paramList.toArray(), EntitySecurityToken.class);
		return tokenList.isEmpty() ? Optional.empty() : Optional.of(tokenList.get(0));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EntitySecurityToken token) {
		appPersistenceServices.mergeEntity(token);
		return true;
	}
}