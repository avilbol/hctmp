/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.services.interfaces;

import com.hallocasa.commons.exceptions.services.ServiceException;
import com.hallocasa.dataentities.NewsletterReceiver;

/**
 *
 * @author David Mantilla
 */
public interface NewsletterServicesInterface {

    void saveNewsletterReceiver(NewsletterReceiver newsletterReceiver) throws ServiceException;

}
