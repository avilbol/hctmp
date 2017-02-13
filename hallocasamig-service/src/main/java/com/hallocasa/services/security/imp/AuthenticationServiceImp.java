/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.security.imp;

import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.Optional;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.services.security.AuthenticationService;
import com.hallocasa.services.security.SecurityTokenService;
import com.hallocasa.utils.constants.exceptions.SecurityException;
import com.hallocasa.utils.security.CodecUtils;
import com.hallocasa.vo.User;
import com.hallocasa.vo.security.AuthInfo;
import com.hallocasa.vo.security.UserCredentials;

/**
 * @author avillamil
 */
@Stateless
public class AuthenticationServiceImp implements AuthenticationService {

	/* static */
	private Logger LOG = Logger.getLogger(AuthenticationServiceImp.class.getName());

	/* dependencies */
	@EJB
	IDAOUser daoUser;
	
	@EJB
	SecurityTokenService securityTokenService;

	/**
	 * Default constructor
	 */
	public AuthenticationServiceImp() {
	}

	@Override
	public AuthInfo authenticate(UserCredentials credentials) throws OAuthSystemException {
		// search user
		Optional<EntityUser> entUser = daoUser.find(credentials.getEmail());
		if (!entUser.isPresent()) {
			throw new SecurityException("Email of user does not exist",
					SecurityException.INVALID_AUTH_CODE);
		}
		if (!entUser.get().getPassword().equals(CodecUtils.encryptPassword(credentials.getPassword()))) {
			throw new SecurityException("Email of user or password is incorrect",
					SecurityException.INVALID_AUTH_CODE);
		}
		if (!entUser.get().getConfirmedFlag()) {
			throw new SecurityException("The user is inactive", 
					SecurityException.FORBIDDEN);
		}
		// creates result object
		User user = (User) toValueObject(entUser.get());
		AuthInfo authInfo = new AuthInfo(user, securityTokenService.generate(user));
		LOG.info("User logged: " + user.getFirstName() + user.getLastName());
		return authInfo;
	}
}
