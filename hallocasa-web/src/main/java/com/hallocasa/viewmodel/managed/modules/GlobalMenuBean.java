/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import com.hallocasa.commons.vo.CredentialVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.LoginFailedException;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 * ViewModel of the global menu section
 *
 * @author David Mantilla
 */
@ManagedBean(name = "globalMenuBean")
@ViewScoped
public class GlobalMenuBean {

    // dependencies
    @Inject
    private NavigationHandler navigationHandler;
    @Inject
    private WebSession webSession;
    @Inject
    private ViewContext viewContext;

   

    /**
     * Listener for item click
     *
     * @param event
     */
    public void processMenuItemClick(ActionEvent event) {
        HallocasaViewEnum pageTo = HallocasaViewEnum.valueOf((String) event.getComponent().getAttributes().get("page"));
        navigationHandler.redirectToPage(pageTo, null);
    }

    /**
     * Process click on the logout
     */
    public void processLogoutClick() {
        webSession.logout();
        navigationHandler.redirectToPage(HallocasaViewEnum.HOME);
    }
    
    public void processGoToProfile() {
        navigationHandler.redirectToPage(HallocasaViewEnum.MY_PROFILE);
    }
    
    /**
     * Getter for showLogoutButton
     *
     * @return showLogoutButton
     */
    public boolean isLogged() {
        return webSession.isLogged();
    }

    public String getUsername(){
        UserVO userVO = webSession.getCurrentUser();
        if(userVO.getFirstName() == null){
            return userVO.getEmail();
        }
        return userVO.getFirstName();
    }
    
}
