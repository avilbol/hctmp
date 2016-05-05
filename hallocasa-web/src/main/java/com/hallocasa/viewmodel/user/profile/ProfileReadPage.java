/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.user.profile;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.services.interfaces.UserServices;
import com.hallocasa.view.utils.FormatUtils;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

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

	/* instance variables */
    private UserVO user;

    /* dependencies */
    @Inject
    private WebSession webSession;
    @Inject
    private NavigationHandler navigationHandler;
    @EJB
    private UserServices userServices;

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
        user = userServices.find(webSession.getCurrentUser().getId());
        if ( user == null ){
            // it should never 
            throw new ForbiddenException();
        }
    }

    /**
     * Process click event over edit button
     */
    public void processEditClick(){
        navigationHandler.redirectToPage(HallocasaViewEnum.EDIT_PROFILE);
    }

    /**
     * Getter for user
     *
     * @return user
     */
    public UserVO getUser() {
        return user;
    }
    
    public String getUsername(){
       return FormatUtils.getDefensiveLabel(user.getFullName());
    }
    
    public boolean isUsernamePending(){
        return user.getFullName().equals("");
    }
    
    public String getCountryName(){
        return user.getCountry().getCountryName().getText(webSession.getCurrentLanguage());
    }
    
    public String getStateName(){
        return user.getState().getStateName().getText(webSession.getCurrentLanguage());
    }
    
    public String getCityName(){
        return user.getCity().getCityName().getText(webSession.getCurrentLanguage());
    }
    
    public String getWebsiteName(){
        return FormatUtils.getDefensiveLabel(user.getWebSite());
    }
    
    public String getWebsiteLink(){
        return FormatUtils.buildWebString(user.getWebSite(), false);
    }
    
    public String getSkypeName(){
        return FormatUtils.getDefensiveLabel(user.getSkype());
    }
    
    public String getLinkedInName(){
        return FormatUtils.getDefensiveLabel(user.getLinkedIn());
    }
    
    public String getLinkedInLink(){
        return FormatUtils.buildWebString(user.getLinkedIn(), true);
    }
    
    public boolean getWebsitePending(){
        return FormatUtils.isEmptyValue(user.getWebSite());
    }
    
    public boolean getTelephonePending(){
        return user.getTelephone() == null ||
        		FormatUtils.isEmptyValue(user.getTelephone().getNumber());
    }
    
    public boolean getSkypePending(){
        return FormatUtils.isEmptyValue(user.getSkype());
    }
    
    public boolean getLinkedInPending(){
        return FormatUtils.isEmptyValue(user.getLinkedIn());
    }

}
