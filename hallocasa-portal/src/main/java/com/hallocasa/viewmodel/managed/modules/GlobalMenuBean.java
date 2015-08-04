/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.modules;

import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;
import com.hallocasa.viewmodel.viewfacade.Page;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "globalMenuBean")
@ViewScoped
public class GlobalMenuBean {

    /**
     * ************************************************************************
     * Constanst
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Instance variable
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Constructor
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    
    /**
     * Listener for item click
     *
     * @param event
     */
    public void onMenuItemClick(ActionEvent event) {
        Page pageTo = Page.valueOf((String) event.getComponent().getAttributes().get("page"));
        AbstractViewFacade.getCurrentInstance().navigate(pageTo, null);
    }

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
}
