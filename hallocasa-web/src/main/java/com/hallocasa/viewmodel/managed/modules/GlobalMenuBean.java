/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import com.hallocasa.model.controlaccess.HallocasaViewEnum;
import com.hallocasa.view.navigation.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "globalMenuBean")
@ViewScoped
public class GlobalMenuBean {
    
    @Inject
    private NavigationHandler navigationHandler;

    
    /**
     * Listener for item click
     *
     * @param event
     */
    public void onMenuItemClick(ActionEvent event) {
        HallocasaViewEnum pageTo = HallocasaViewEnum.valueOf((String) event.getComponent().getAttributes().get("page"));
        navigationHandler.redirectToPage(pageTo, null);
    }

}
