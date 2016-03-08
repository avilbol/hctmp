/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import com.google.gson.Gson;
import com.hallocasa.commons.users.UserUtils;
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
    
    private static Gson gson = new Gson();

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
    
    public String getJsEmail(){
    	return gson.toJson(getEmail());
    }
    
    public String getJsUsername(){
    	return gson.toJson(getUsername());
    }
    
    public String getUsername(){
    	return UserUtils.getUsername(webSession.getCurrentUser());
    }
    
    public String getFullusername(){
    	return UserUtils.getFullUsername(webSession.getCurrentUser());
    }
    
    public String getEmail(){
    	UserVO userVO = webSession.getCurrentUser();
    	if(userVO == null){
    		return null;
    	}
    	return userVO.getEmail();
    }
}
