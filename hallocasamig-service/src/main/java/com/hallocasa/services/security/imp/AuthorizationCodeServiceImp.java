package com.hallocasa.services.security.imp;

import static com.hallocasa.utils.constants.HallocasaEnv.AUTHORIZATION_CODE_LENGTH;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toEntity;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;
import static com.hallocasa.utils.security.EncryptionUtils.hashMd5;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.security.IDAOAuthorizationCode;
import com.hallocasa.entities.security.EntityAuthorizationCode;
import com.hallocasa.services.security.AuthorizationCodeService;
import com.hallocasa.utils.constants.exceptions.SecurityException;
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
		Optional<EntityAuthorizationCode> optAntAuthCode = daoAuthorizationCode.find(clientId, null);
		boolean foundAuthCode = false;
		if (optAntAuthCode.isPresent()) {
			String encAuthCode = hashMd5(authorizationCode);
			foundAuthCode = encAuthCode.equals(optAntAuthCode.get().getAuthCode());
		}
		return foundAuthCode ? (Optional<AuthorizationCode>) toValueObject(optAntAuthCode) : Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorizationCode generate(String clientId) {
		String authCodeCandidate = null;
		String encAuthCodeCandidate = null;
		boolean authCodeIncompatible = false;
		do {
			authCodeCandidate = randomAlphabetic(AUTHORIZATION_CODE_LENGTH);
			encAuthCodeCandidate = hashMd5(authCodeCandidate);
			Optional<EntityAuthorizationCode> authCode = daoAuthorizationCode.find(null, encAuthCodeCandidate);
			authCodeIncompatible = authCode.isPresent() && !authCode.get().getClientId().equals(clientId);
		} while (authCodeIncompatible);
		EntityAuthorizationCode clientAuthCode = buildAuthCode(clientId, encAuthCodeCandidate);
		daoAuthorizationCode.save(clientAuthCode);
		AuthorizationCode authCode = (AuthorizationCode) toValueObject(clientAuthCode);
		authCode.setAuthCode(authCodeCandidate);
		return authCode;
	}

	private EntityAuthorizationCode buildAuthCode(String clientId, String authCodeCandidate) {
		Optional<EntityAuthorizationCode> optClientAuthCode = daoAuthorizationCode.find(clientId, null);
		if (!optClientAuthCode.isPresent()) {
			AuthorizationCode authCode = new AuthorizationCode(clientId, authCodeCandidate);
			return (EntityAuthorizationCode) toEntity(authCode);
		} else {
			EntityAuthorizationCode authCode = optClientAuthCode.get();
			authCode.setAuthCode(authCodeCandidate);
			return authCode;
		}
	}
}
