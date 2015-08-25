/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.sections.globalmenu;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hallocasa.dataentities.TemporalPublisherUser;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.commons.constants.ViewBundle;
import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;
import com.hallocasa.viewmodel.managed.modules.NewsletterSubscription;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "publishPropertyRegister")
@ViewScoped
public class PublishPropertyRegister extends BaseManagedBean {

    /* Static variables */
    private static final long serialVersionUID = 2705795344585383765L;

    /* Dependences */
    private TemporalPublisherUser publisherUser;
    @EJB
    private UserServices userServices;

    /**
     * Postconstruct
     */
    @PostConstruct
    public void initialize() {
        publisherUser = new TemporalPublisherUser();
    }

    /* Methods */
    /**
     * Submit the temporal user register
     */
    public void submit() {
        // Save the email
        try {
            publisherUser.setLanguage(WebSessionImpl.getCurrentInstance()
                    .getCurrentLanguage());
            userServices.savePropertyPublisher(publisherUser);
            publisherUser = new TemporalPublisherUser();
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
                getBundleMessage(ViewBundle.MESSAGE_NEWSLETTER_THANKS), "");

        // Reset the form
        initialize();
    }

    /* Getter y Setters */
    /**
     * @return the publisherUser
     */
    public TemporalPublisherUser getPublisherUser() {
        return publisherUser;
    }

    /**
     * @param publisherUser
     *            the publisherUser to set
     */
    public void setPublisherUser(TemporalPublisherUser publisherUser) {
        this.publisherUser = publisherUser;
    }
}
