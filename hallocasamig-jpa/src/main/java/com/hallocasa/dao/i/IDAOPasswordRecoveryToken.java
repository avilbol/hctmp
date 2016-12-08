package com.hallocasa.dao.i;

import com.hallocasa.entities.EntityPasswordRecoveryToken;

public interface IDAOPasswordRecoveryToken {

	Boolean saveToken(EntityPasswordRecoveryToken token);

	EntityPasswordRecoveryToken getToken(String tokenContent);

	Boolean deleteToken(Integer idAssociated);
}
