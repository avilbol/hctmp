package com.hallocasa.services.messaging.local;

import java.util.List;

import com.hallocasa.vo.Language;
import com.hallocasa.vo.mail.MailChimpList;
import com.hallocasa.vo.mail.MailChimpMergeVars;


/**
 * Interface for mail Chimp Api services
 * 
 * @author David Mantilla
 *
 */
public interface MailChimpServices {

    /**
     * Subscribe new user
     * 
     * @param email
     * @param firstName
     * @param lastName
     * @param language
     * @param type
     * @throws MailChimpException
     */
    public void subscribeNewUser(String email, String firstName,
            String lastName, Language language, MailChimpMergeVars.TypeEnum type);

    /**
     * Find the mail lists
     * 
     * @return the list of mail chimp lists in the account
     */
    public List<MailChimpList> findLists();
}
