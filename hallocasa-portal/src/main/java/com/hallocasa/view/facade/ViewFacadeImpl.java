/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.facade;

import com.hallocasa.view.navigation.NavigationHandler;
import com.hallocasa.viewmodel.viewfacade.Page;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 * Implementation of the view facade, for the JSF framework
 *
 * @author David Mantilla
 */
public class ViewFacadeImpl extends AbstractViewFacade {

    /* instance variables */
    private final NavigationHandler navigationHandler;

    /**
     * Default constructor
     */
    public ViewFacadeImpl() {
        this.navigationHandler = new NavigationHandler();
    }

    @Override
    public void navigate(Page page, Map<String, String> params) {
        navigationHandler.redirectToPage(page, params);
    }

    @Override
    public String buildAbsoluteUrl(Page page, Map<String, String> params) {
        return navigationHandler.buildAbsoluteUrl(page, params);
    }

    @Override
    public Map<String, String> getViewParams() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

}
