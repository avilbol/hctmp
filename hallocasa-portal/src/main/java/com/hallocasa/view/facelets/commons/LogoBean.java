/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.facelets.commons;

import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;
import com.hallocasa.viewmodel.viewfacade.Page;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "logoBean")
@RequestScoped
public class LogoBean {

    /**
     * Listener for the click on the logo
     *
     * @param event
     */
    public void onLogoClick(ActionEvent event) {
        AbstractViewFacade.getCurrentInstance().navigate(Page.HOME, null);
    }

}
