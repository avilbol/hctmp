/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services;

import com.hallocasa.dataentities.wcm.NewsletterReceiver;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.hallocasa.services.interfaces.MailChimpServicesLocal;
import com.hallocasa.services.interfaces.NewsletterServicesInterface;
import com.hallocasa.vo.MailChimpMergeVars.TypeEnum;

/**
 *
 * @author David Mantilla
 */
@Stateless
public class NewsletterServices implements NewsletterServicesInterface {

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(NewsletterServices.class
            .getName());

    /* Dependences */
    @PersistenceContext(unitName = "RealStateDatabasePU")
    private EntityManager em;
    @EJB
    private MailChimpServicesLocal mailChimpServices;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hallocasa.business.services.interfaces.NewsletterServicesInterface
     * #saveNewsletterReceiver
     * (com.hallocasa.dataentities.NewsletterReceiver)
     */
    @Override
    public void saveNewsletterReceiver(NewsletterReceiver newsletterReceiver) {
        em.persist(newsletterReceiver);
        mailChimpServices.subscribeNewUser(newsletterReceiver.getEmail(), "",
                "", newsletterReceiver.getLanguage(), TypeEnum.NEWSLETTER);
    }
}
