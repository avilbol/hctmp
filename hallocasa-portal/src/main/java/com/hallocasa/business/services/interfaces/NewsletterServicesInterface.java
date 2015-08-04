/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.services.interfaces;

import com.hallocasa.business.dataentities.NewsletterReceiver;
import com.hallocasa.business.exceptions.ServiceException;

/**
 *
 * @author David Mantilla
 */
public interface NewsletterServicesInterface {

    void saveNewsletterReceiver(NewsletterReceiver newsletterReceiver) throws ServiceException;

}
