/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.impl;

import com.hallocasa.commons.codec.CodecUtils;
import com.hallocasa.commons.exceptions.services.InvalidEmailException;
import com.hallocasa.commons.validation.StandardPropertyValidator;
import com.hallocasa.commons.vo.RegisterUserVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.helpers.ParsersContext;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.BuildInMailType;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.user.local.SignUpServices;
import com.hallocasa.vo.MailChimpMergeVars;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */
@Stateless
public class SignUpServicesImpl extends ServicesBase implements SignUpServices {

    @EJB
    private MailServices mailServices;
    @EJB
    private AppPersistenceServices appPersistenceServices;
    @EJB
    private MailChimpServices mailChimpServices;

    /**
     * Default constructor
     */
    public SignUpServicesImpl() {
    }

    /**
     * Dependencies injection
     *
     * @param mailServices
     * @param appPersistenceServices
     * @param mailChimpServices
     */
    public SignUpServicesImpl(AppPersistenceServices appPersistenceServices,
            MailServices mailServices, MailChimpServices mailChimpServices) {
        this.mailServices = mailServices;
        this.appPersistenceServices = appPersistenceServices;
        this.mailChimpServices = mailChimpServices;
    }

    /* Implementation */
    @Override
    public void sendActivationLinkEmail(long userId, String activationLink,
            String activationKey) throws MailServicesErrorException {

        // set values to validate
        StandardPropertyValidator bean = new StandardPropertyValidator();
        bean.setUrl(activationLink);

        // validate properties
        validateProperty(bean, "url", StandardPropertyValidator.class);

        // set values to validate
        User user = findAndValidateEntity(User.class, userId,
                appPersistenceServices);

        // sends email
        List<String> emails = new ArrayList<>();
        emails.add(user.getEmail());
        Map<String, String> params = new HashMap<>();
        params.put("USER_ACTIVATION_LINK", activationLink);
        params.put("USER_ACTIVATION_KEY", activationKey);
        mailServices.sendMail(BuildInMailType.USER_ACTIVATION,
                user.getLanguage().getLocale(), emails, params);
    }

    @Override
    public void activateUser(long userId) {
        User user = findAndValidateEntity(User.class, userId,
                appPersistenceServices);
        user.setConfirmedFlag(true);
        appPersistenceServices.mergeEntity(user);
    }

    @Override
    public UserVO registerUser(RegisterUserVO registerUserVO)
            throws InvalidEmailException {

        // validates bean
        validateBean(registerUserVO, RegisterUserVO.class);

        // search an existing email with the given user
        List<User> usersWithEmail
                = appPersistenceServices.executeNamedQuery(
                        User.QUERY_FIND_BY_EMAIL, new Object[]{
                            registerUserVO.getEmail()}, User.class);
        if (!usersWithEmail.isEmpty()) {
            throw new InvalidEmailException();
        }

        // creates the new entity
        User user = new User();
        user.setEmail(registerUserVO.getEmail());
        user.setPassword(CodecUtils.encryptPassword(registerUserVO.getPassword()));
        user.setLanguage(registerUserVO.getLanguage());
        user.setConfirmedFlag(Boolean.FALSE);

        // persists the new entity and creates value object to return
        appPersistenceServices.persistEntity(user);
        appPersistenceServices.flush();

        // register user in mailchimp
        mailChimpServices.subscribeNewUser(user.getEmail(), "", "",
                user.getLanguage(), MailChimpMergeVars.TypeEnum.PUBLISHER);

        UserVO userVO = ParsersContext.USER_VO_PARSER.toValueObject(
                user, UserVO.class);
        return userVO;
    }
}