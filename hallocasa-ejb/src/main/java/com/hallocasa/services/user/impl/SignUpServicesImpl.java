/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.user.impl;

import com.hallocasa.commons.validation.StandardPropertyValidator;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.services.base.ServicesBase;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.services.messaging.local.MailServices.BuildInMailType;
import com.hallocasa.services.persistence.local.AppPersistenceServices;
import com.hallocasa.services.user.local.SignUpServices;
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
     */
    public SignUpServicesImpl(AppPersistenceServices appPersistenceServices,
            MailServices mailServices) {
        this.mailServices = mailServices;
        this.appPersistenceServices = appPersistenceServices;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobiera.social.services.local.AccountServicesLocal#
     * sendActivationLinkEmail(long)
     */
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

}
