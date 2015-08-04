package com.hallocasa.business.services.interfaces;

import java.util.List;

import com.hallocasa.business.exceptions.MailChimpException;
import com.hallocasa.business.vo.MailChimpList;
import com.hallocasa.business.vo.MailChimpMergeVars.TypeEnum;
import com.hallocasa.commons.Language;

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
     * @throws MailChimpException
     */
    public void subscribeNewUser(String email, String firstName,
            String lastName, Language language, TypeEnum type)
            throws MailChimpException;

    /**
     * Find the mail lists
     * 
     * @return the list of mail chimp lists in the account
     */
    public List<MailChimpList> findLists();
}
