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
import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.Language;
import com.hallocasa.vo.mail.MailChimpList;
import com.hallocasa.vo.mail.MailChimpMergeVars;
import com.hallocasa.vo.mail.MailChimpMergeVars.TypeEnum;
import static com.hallocasa.systemproperties.SystemProperty.*;
import static com.hallocasa.systemproperties.SystemConstants.*;
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
        subscribeMethod.apikey = get(MAIL_CHIMP_API_KEY);
        subscribeMethod.id = get(MAIL_CHIMP_TEST1_LIST_ID);
        subscribeMethod.email = new Email();
        subscribeMethod.email.email = email;
        subscribeMethod.double_optin = false;
        subscribeMethod.update_existing = true;
        subscribeMethod.merge_vars = new MailChimpMergeVars(email, firstName,
                lastName, language.getLocale().toString(), typeEnum.name());
        try {
            mailChimpClient.execute(subscribeMethod);
        } catch (IOException | com.ecwid.mailchimp.MailChimpException e) {
            throw new FatalException("Unexpected error", e);
        } finally {
            mailChimpClient.close();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MailChimpList> findLists() {
        MailChimpClient mailChimpClient = new MailChimpClient();
        ListMethod listMethod = new ListMethod();
        listMethod.apikey = get(MAIL_CHIMP_API_KEY);
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
            throw new FatalException("Unexpected error", e);
        } finally {
            mailChimpClient.close();
        }
    }
}
