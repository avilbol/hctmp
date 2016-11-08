package com.hallocasa.dao.security;

import static com.hallocasa.entities.security.EntityAuthorizationCode.QUERY_FIND_BY_AUTHCODE;
import static com.hallocasa.entities.security.EntityAuthorizationCode.QUERY_FIND_BY_CLIENT_ID;
import static com.hallocasa.entities.security.EntityAuthorizationCode.QUERY_FIND_BY_IDENTIFIER;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.security.IDAOAuthorizationCode;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.security.AuthorizationCode;

/**
 * DAO for class {@link AuthorizationCode}
 */
@Stateless
public class DAOAuthorizationCode implements IDAOAuthorizationCode {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	@Override
	public Optional<EntityAuthorizationCode> find(String clientId, String authCode) {
		String query = (clientId != null && authCode != null) ? QUERY_FIND_BY_IDENTIFIER
				: (clientId != null ? QUERY_FIND_BY_CLIENT_ID : QUERY_FIND_BY_AUTHCODE);
		List<Object> paramList = new ArrayList<Object>();
		if (authCode != null) 
			paramList.add(authCode);
		if (clientId != null)
			paramList.add(clientId);
		List<EntityAuthorizationCode> authCodeList = appPersistenceServices.executeNamedQuery(query,
				paramList.toArray(), EntityAuthorizationCode.class);
		return authCodeList.isEmpty() ? Optional.empty() : Optional.of(authCodeList.get(0));
	}

	@Override
	public boolean save(EntityAuthorizationCode authorizationCode) {
		appPersistenceServices.mergeEntity(authorizationCode);
		return true;
	}

}
