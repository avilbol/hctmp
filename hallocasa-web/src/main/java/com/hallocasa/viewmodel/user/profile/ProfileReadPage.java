/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.utils.FormatUtils;

/**
 * View model for profile page
 *
 * @author David Mantilla
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class ProfileReadPage implements Serializable {

    /**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -6006649582382274792L;

    /* dependencies */
    @Inject
    private WebSession webSession;
    @EJB
    private UserServices userServices;
    
    @ManagedProperty(value="#{globalProfilePage}")
    private GlobalProfilePage globalProfilePage;

    /**
     * Default constructor
     */
    public ProfileReadPage() {
    }
    /**
     * Initialize the bean
     */
    @PostConstruct
    public void initialize() {
        // Nothing to do
    }

    /**
     * Process click event over edit button
     */
    public void processEditClick(){
    	globalProfilePage.goToEditProfile();
    }
    
    /**
     * Getter for user
     *
     * @return user
     */
    public UserVO getUser() {
        return globalProfilePage.getUser();
    }
    
    public String getUsername(){
       return FormatUtils.getDefensiveLabel(this.getUser().getFullName());
    }
    
    public boolean isUsernamePending(){
        return this.getUser().getFullName().equals("");
    }
    
    public String getCountryName(){
        return this.getUser().getCountry().getCountryName().getText(webSession.getCurrentLanguage());
    }
    
    public String getStateName(){
        return this.getUser().getState().getStateName().getText(webSession.getCurrentLanguage());
    }
    
    public String getCityName(){
        return this.getUser().getCity().getCityName().getText(webSession.getCurrentLanguage());
    }
    
    public String getWebsiteName(){
        return FormatUtils.getDefensiveLabel(this.getUser().getWebSite());
    }
    
    public String getWebsiteLink(){
        return FormatUtils.buildWebString(this.getUser().getWebSite(), false);
    }
    
    public String getSkypeName(){
        return FormatUtils.getDefensiveLabel(this.getUser().getSkype());
    }
    
    public String getLinkedInName(){
        return FormatUtils.getDefensiveLabel(this.getUser().getLinkedIn());
    }
    
    public String getLinkedInLink(){
        return FormatUtils.buildWebString(this.getUser().getLinkedIn(), true);
    }
    
    public boolean getWebsitePending(){
        return FormatUtils.isEmptyValue(this.getUser().getWebSite());
    }
    
    public boolean getTelephonePending(){
        return this.getUser().getTelephone() == null ||
        		FormatUtils.isEmptyValue(this.getUser().getTelephone().getNumber());
    }
    
    public boolean getSkypePending(){
        return FormatUtils.isEmptyValue(this.getUser().getSkype());
    }
    
    public boolean getLinkedInPending(){
        return FormatUtils.isEmptyValue(this.getUser().getLinkedIn());
    }
    
	public GlobalProfilePage getGlobalProfilePage() {
		return globalProfilePage;
	}
	
	public void setGlobalProfilePage(GlobalProfilePage globalProfilePage) {
		this.globalProfilePage = globalProfilePage;
	}
}
