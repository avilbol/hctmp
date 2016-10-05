package com.hallocasa.services.security.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

import javax.ejb.EJB;

import com.hallocasa.dao.i.security.IDAOSecurityToken;
import com.hallocasa.entities.security.EntitySecurityToken;
import com.hallocasa.services.security.SecurityTokenService;
import com.hallocasa.utils.constants.errormessages.SecurityTokenError;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.vo.security.SecurityToken;

public class SecurityTokenImp implements SecurityTokenService {

	@EJB
	private IDAOSecurityToken daoSecurityToken;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(String tokenValue) throws SecurityException {
		Optional<EntitySecurityToken> optEntSecToken = daoSecurityToken.find(tokenValue);
		if (!optEntSecToken.isPresent()) {
			throw new SecurityException(SecurityTokenError.TOKEN_NOT_REGISTERED);
		}
		EntitySecurityToken entSecToken = optEntSecToken.get();
		Long totalMilliesPassed = entSecToken.getRegistered().getTime() + entSecToken.getExpiresIn();
		if(totalMilliesPassed <= System.currentTimeMillis()){
			throw new SecurityException(SecurityTokenError.EXPIRED_TOKEN);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecurityToken generate() {
		// TODO Auto-generated method stub
		return null;
	}

}
