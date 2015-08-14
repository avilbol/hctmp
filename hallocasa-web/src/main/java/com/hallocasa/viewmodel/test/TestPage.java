/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.test;

import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServicesLocal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author david
 */
@ManagedBean(name = "testPage")
@ViewScoped
public class TestPage {

    @EJB
    private MailServicesLocal mailServices;

    public void sendTestEmail(ActionEvent event) {
        try {
            List<String> emails = new ArrayList<String>();
            emails.add("dmantil@hotmail.com");
            mailServices.sendMail(MailServicesLocal.BuildInMailType.RESET_PASSWORD,
                    Locale.US, emails, new HashMap<String, String>());
            Logger.getLogger(TestPage.class.getName()).log(Level.INFO, "Mail sent succesfully");
        } catch (MailServicesErrorException ex) {
            Logger.getLogger(TestPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
