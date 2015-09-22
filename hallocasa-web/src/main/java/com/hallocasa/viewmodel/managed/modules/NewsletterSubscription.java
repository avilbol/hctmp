/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hallocasa.dataentities.wcm.NewsletterReceiver;
import com.hallocasa.services.interfaces.NewsletterServicesInterface;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.view.i18n.Messages;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "newsletterSubscription")
@ViewScoped
public class NewsletterSubscription extends BaseManagedBean implements
        Serializable {

    private static final long serialVersionUID = 1796655203295863876L;
    private NewsletterReceiver newsletterReceiver;
    @EJB
    private NewsletterServicesInterface newsletterServices;

    public NewsletterSubscription() {
    }

    @PostConstruct
    public void initialize() {
        newsletterReceiver = new NewsletterReceiver();
    }

    public NewsletterReceiver getNewsletterReceiver() {
        return newsletterReceiver;
    }

    public void setNewsletterReceiver(NewsletterReceiver newsletterReceiver) {
        this.newsletterReceiver = newsletterReceiver;
    }

    public void submit() {
        try {
            newsletterReceiver.setLanguage(WebSessionImpl.getCurrentInstance()
                    .getCurrentLanguage());
            newsletterServices.saveNewsletterReceiver(newsletterReceiver);
            newsletterReceiver = new NewsletterReceiver();

        } catch (ServiceException ex) {
            Logger.getLogger(NewsletterSubscription.class.getName()).log(
                    Level.SEVERE, null, ex);
            addFacesMessage(null, FacesMessage.SEVERITY_ERROR,
                    ex.getLocalizedMessage(WebSessionImpl.getCurrentInstance()
                            .getCurrentLanguage()), "");
            return;
        }

        // show a successful email
        addFacesMessage(null, FacesMessage.SEVERITY_INFO,
                getBundleMessage(Messages.MESSAGE_NEWSLETTER_THANKS), "");
    }

}
