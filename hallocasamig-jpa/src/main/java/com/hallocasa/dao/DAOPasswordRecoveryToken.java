package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOPasswordRecoveryToken;
import com.hallocasa.entities.EntityPasswordRecoveryToken;
import com.hallocasa.jpaservices.i.AppPersistenceServices;


@Stateless
public class DAOPasswordRecoveryToken implements IDAOPasswordRecoveryToken {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	@Override
	public Boolean saveToken(EntityPasswordRecoveryToken token) {
		appPersistenceServices.mergeEntity(token);
		return true;
	}

	@Override
	public EntityPasswordRecoveryToken getToken(String tokenContent) {
		List<EntityPasswordRecoveryToken> tokenList = appPersistenceServices.executeNamedQuery(EntityPasswordRecoveryToken.QUERY_FIND_BY_CONTENT_AND_ACTIVE,
				new Object[] { tokenContent }, EntityPasswordRecoveryToken.class);
		if (tokenList != null && tokenList.size() > 0) {
			return tokenList.get(0);
		}
		return null;
	}

	@Override
	public Boolean deleteToken(Integer idAssociated) {
		appPersistenceServices.executeNamedQuery(EntityPasswordRecoveryToken.QUERY_DELETE_BY_ID, new Object[] { idAssociated });
		return true;
	}
}
