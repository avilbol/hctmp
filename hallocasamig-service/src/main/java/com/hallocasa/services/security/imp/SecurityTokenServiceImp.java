package com.hallocasa.services.security.imp;

import java.util.Date;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.hallocasa.dao.i.security.IDAOSecurityToken;
import com.hallocasa.entities.security.EntitySecurityToken;
import com.hallocasa.services.security.SecurityTokenService;
import com.hallocasa.utils.constants.HallocasaEnv;
import com.hallocasa.utils.constants.errormessages.SecurityTokenError;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.utils.constants.parsing.HallocasaConvert;
import com.hallocasa.vo.security.SecurityToken;

@Stateless
public class SecurityTokenServiceImp implements SecurityTokenService {

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
	public SecurityToken generate() throws OAuthSystemException {
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String candidateToken = null;
        do{
        	candidateToken = oauthIssuerImpl.accessToken();
        }
        while(daoSecurityToken.find(candidateToken).isPresent());
        EntitySecurityToken entSecToken = new EntitySecurityToken();
        entSecToken.setTokenValue(candidateToken);
        entSecToken.setRegistered(new Date());
        entSecToken.setExpiresIn(HallocasaEnv.MILISECONDS_TOKEN_EXPIRES_IN);
        daoSecurityToken.save(entSecToken);
        return (SecurityToken) HallocasaConvert.toValueObject(entSecToken);
	}

}
