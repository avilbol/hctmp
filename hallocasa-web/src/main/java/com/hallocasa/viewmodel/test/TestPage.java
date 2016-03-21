/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.test;

import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.dataentities.app.TestEntity;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    private MailServices mailServices;
    
    private TestEntity testAttr;
    
    private TelephoneVO telephoneAttr;
    
    private String myAttr;
    
    private List<String> availableElements;
    
    private List<String> selectedElements;
    
    @PostConstruct
    public void init(){
        availableElements = new ArrayList<String>();
        availableElements.add("A");
        availableElements.add("B");
        availableElements.add("C");
        
        selectedElements = new ArrayList<String>();
        //selectedElements.add("A");
        selectedElements.add("B");
        selectedElements.add("C");
    }

    public void onAction(){
    	System.out.println(testAttr);
    }

    public void sendTestEmail(ActionEvent event) {
        try {
            List<String> emails = new ArrayList<String>();
            emails.add("dmantil@hotmail.com");
            mailServices.sendMail(MailServices.BuildInMailType.RESET_PASSWORD,
                    Locale.US, emails, new HashMap<String, String>());
            Logger.getLogger(TestPage.class.getName()).log(Level.INFO, "Mail sent succesfully");
        } catch (MailServicesErrorException ex) {
            Logger.getLogger(TestPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getAvailableElements() {
        return availableElements;
    }

    public void setAvailableElements(List<String> availableElements) {
        this.availableElements = availableElements;
    }

    public List<String> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(List<String> selectedElements) {
        this.selectedElements = selectedElements;
    }

	public TestEntity getTestAttr() {
		return testAttr;
	}

	public void setTestAttr(TestEntity testAttr) {
		this.testAttr = testAttr;
	}

	public String getMyAttr() {
		return myAttr;
	}

	public void setMyAttr(String myAttr) {
		this.myAttr = myAttr;
	}

	public TelephoneVO getTelephoneAttr() {
		return telephoneAttr;
	}

	public void setTelephoneAttr(TelephoneVO telephoneAttr) {
		this.telephoneAttr = telephoneAttr;
	}
}
