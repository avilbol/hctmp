/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.facelets.commons;

import com.hallocasa.view.navigation.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "logoBean")
@RequestScoped
public class LogoBean {
    
    @Inject
    private NavigationHandler navigationHandler;

    /**
     * Listener for the click on the logo
     *
     * @param event
     */
    public void onLogoClick(ActionEvent event) {
        navigationHandler.redirectToPage(HallocasaViewEnum.HOME, null);
    }

}
