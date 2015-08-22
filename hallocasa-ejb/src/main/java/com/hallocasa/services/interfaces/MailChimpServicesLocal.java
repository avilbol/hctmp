package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.Language;
import com.hallocasa.exceptions.MailChimpException;
import com.hallocasa.vo.MailChimpList;
import com.hallocasa.vo.MailChimpMergeVars;


/**
 * Interface for mail Chimp Api services
 * 
 * @author David Mantilla
 *
 */
public interface MailChimpServicesLocal {

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
            String lastName, Language language, MailChimpMergeVars.TypeEnum type)
            throws MailChimpException;

    /**
     * Find the mail lists
     * 
     * @return the list of mail chimp lists in the account
     */
    public List<MailChimpList> findLists();
}
