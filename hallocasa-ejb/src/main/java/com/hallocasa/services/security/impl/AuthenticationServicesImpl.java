/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.security.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.exceptions.services.InvalidPasswordLoginException;
import com.hallocasa.commons.vo.AppAccessInfoVO;
import com.hallocasa.commons.vo.AuthInfoVO;
import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.security.local.AuthenticationServices;

/**
 *
 * @author david
 */
@Stateless
public class AuthenticationServicesImpl extends ServicesBase implements AuthenticationServices {

    /* static */
    private Logger LOG = Logger.getLogger(AuthenticationServicesImpl.class.getName());

    /* dependencies */
    @EJB
    private AppPersistenceServices appPS;

    /**
     * Default constructor
     */
    public AuthenticationServicesImpl() {
    }

    /**
     * Constructor with dependencies injection
     *
     * @param appPS
     */
    public AuthenticationServicesImpl(AppPersistenceServices appPS) {
        this.appPS = appPS;
    }

    @Override
    public AuthInfoVO authenticate(CredentialVO credentials) throws
            InvalidEmailException, InvalidPasswordLoginException {

        // validates bean
        validateBean(credentials, CredentialVO.class);

        // search user
        List<User> users
                = appPS.executeNamedQuery(User.QUERY_FIND_BASIC_BY_EMAIL,
                        new Object[]{credentials.getEmail()}, User.class);

        if (users.isEmpty()) {
            throw new InvalidEmailException();
        }
        if (!users.get(0).getPassword().equals(
                CodecUtils.encryptPassword(credentials.getPassword()))) {
            throw new InvalidPasswordLoginException();
        }

        // creates result object
        UserVO userVO
                = ParsersContext.USER_VO_PARSER.toValueObject(users.get(0), UserVO.class);
        AuthInfoVO authInfoVO = new AuthInfoVO();
        authInfoVO.setUser(userVO);
        return authInfoVO;
    }

    @Override
    public AppAccessInfoVO getAppAccessInfo(long userId) {
        // TODO:
        LOG.log(Level.WARNING, "Not yet done");
        AppAccessInfoVO appAccessInfoVO = new AppAccessInfoVO();
        return appAccessInfoVO;
    }

}
