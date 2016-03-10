/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.hallocasa.commons.users.UserUtils;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;

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
    
    /**
	 * Flag that indicates if it is possible to  exit of login module
	 * and interact with the app
	 */
	private boolean interactWithApp;
	
    
    private static Gson gson = new Gson();

    @PostConstruct
    public void initialize(){
    	interactWithApp = true;
    }
    
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
    
    public void denyLoginInteract(){
		interactWithApp = false;
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
    
    public boolean isInteractWithApp() {
		return interactWithApp;
	}

	public void setInteractWithApp(boolean interactWithApp) {
		this.interactWithApp = interactWithApp;
	}
}
