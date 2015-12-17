/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.user.profile;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.controlaccess.ForbiddenException;
import com.hallocasa.model.session.WebSession;

import com.hallocasa.services.interfaces.UserServices;import com.hallocasa.view.navigation.HallocasaViewEnum;import com.hallocasa.view.navigation.HallocasaViewNames;
import com.hallocasa.view.navigation.NavigationHandler;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * View model for profile page
 *
 * @author David Mantilla
 * @since 1.7
 */
@Named(value = HallocasaViewNames.USER_PROFILE_VIEW)
@ViewScoped
public class ProfileReadPage implements Serializable {

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
        // navigationHandler.redirectToPage(HallocasaViewEnum.);
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

}
