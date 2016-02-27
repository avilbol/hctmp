package com.hallocasa.services.messaging.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.ListMethod;
import com.ecwid.mailchimp.method.v2_0.lists.ListMethodResult;
import com.ecwid.mailchimp.method.v2_0.lists.SubscribeMethod;
import com.hallocasa.services.messaging.local.MailChimpServices;
import com.hallocasa.commons.Language;
import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.exceptions.MailChimpException;
import com.hallocasa.vo.MailChimpList;
import com.hallocasa.vo.MailChimpMergeVars;
import com.hallocasa.vo.MailChimpMergeVars.TypeEnum;

/**
 * Implementation of mail chimp services
 * 
 * @author David Mantilla
 *
 */
@Stateless
public class MailChimpServicesImpl implements MailChimpServices {

    @Override
    public void subscribeNewUser(String email, String firstName,
            String lastName, Language language, TypeEnum typeEnum) {

        // reuse the same MailChimpClient object whenever possible
        MailChimpClient mailChimpClient = new MailChimpClient();
        SubscribeMethod subscribeMethod = new SubscribeMethod();

        subscribeMethod.apikey = SystemConstants.MAIL_CHIMP_API_KEY;
        subscribeMethod.id = SystemConstants.MAIL_CHIMP_TEST1_LIST_ID;
        subscribeMethod.email = new Email();
        subscribeMethod.email.email = email;
        subscribeMethod.double_optin = false;
        subscribeMethod.update_existing = true;
        subscribeMethod.merge_vars = new MailChimpMergeVars(email, firstName,
                lastName, language.name(), typeEnum.name());
        try {
            mailChimpClient.execute(subscribeMethod);
        } catch (IOException | com.ecwid.mailchimp.MailChimpException e) {
            throw new MailChimpException(e);
        } finally {
            mailChimpClient.close();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hallocasa.business.services.interfaces.MailChimpServicesLocal#findLists
     * ()
     */
    @Override
    public List<MailChimpList> findLists() {
        MailChimpClient mailChimpClient = new MailChimpClient();
        ListMethod listMethod = new ListMethod();
        listMethod.apikey = SystemConstants.MAIL_CHIMP_API_KEY;

        try {
            ListMethodResult result = mailChimpClient.execute(listMethod);
            List<MailChimpList> list = new ArrayList<>();
            for (ListMethodResult.Data d : result.data) {
                MailChimpList mailChimpList = new MailChimpList();
                mailChimpList.setName(d.name);
                mailChimpList.setId(d.id);
                list.add(mailChimpList);
            }
            return list;
        } catch (IOException | com.ecwid.mailchimp.MailChimpException e) {
            throw new MailChimpException(e);
        } finally {
            mailChimpClient.close();
        }
    }

}
