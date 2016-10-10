package com.hallocasa.services.security.imp;

import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang.RandomStringUtils;

import com.hallocasa.dao.i.security.IDAOAuthorizationCode;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.services.security.AuthorizationCodeService;
import com.hallocasa.utils.constants.HallocasaEnv;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.security.AuthorizationCode;

/**
 * Contract implementation to class {@link AuthorizationCode}
 */
@Stateless
public class AuthorizationCodeServiceImp implements AuthorizationCodeService {

	@EJB
	private IDAOAuthorizationCode daoAuthorizationCode;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Optional<AuthorizationCode> find(String clientId, String authorizationCode) {
		Optional<EntityAuthorizationCode> optAntAuthCode = daoAuthorizationCode.find(clientId, authorizationCode);
		return (Optional<AuthorizationCode>)toValueObject(optAntAuthCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorizationCode generate(String clientId) {
		String authCodeCandidate = null;
		boolean authCodeIncompatible = false;
		do {
			authCodeCandidate = RandomStringUtils.randomAlphabetic(HallocasaEnv.AUTHORIZATION_CODE_LENGTH);
			Optional<EntityAuthorizationCode> authCode = daoAuthorizationCode.find(null, authCodeCandidate);
			authCodeIncompatible = authCode.isPresent() && !authCode.get().getClientId().equals(clientId);
		} while (authCodeIncompatible);
		EntityAuthorizationCode clientAuthCode = buildAuthCode(clientId, authCodeCandidate);
		daoAuthorizationCode.save(clientAuthCode);
		return (AuthorizationCode) HallocasaConvert.toValueObject(clientAuthCode);
	}

	private EntityAuthorizationCode buildAuthCode(String clientId, String authCodeCandidate) {
		Optional<EntityAuthorizationCode> optClientAuthCode = daoAuthorizationCode.find(clientId, null);
		if (!optClientAuthCode.isPresent()) {
			AuthorizationCode authCode = new AuthorizationCode(clientId, authCodeCandidate);
			return (EntityAuthorizationCode) HallocasaConvert.toEntity(authCode);
		} else {
			EntityAuthorizationCode authCode = optClientAuthCode.get();
			authCode.setAuthCode(authCodeCandidate);
			return authCode;
		}
	}
}
