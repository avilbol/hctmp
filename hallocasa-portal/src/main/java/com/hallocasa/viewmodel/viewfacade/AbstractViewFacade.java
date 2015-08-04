/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.viewfacade;

import com.hallocasa.view.facade.ViewFacadeImpl;
import java.util.Map;

/**
 * Facade for the view. All the view coupling should be concentrated here in
 * order to avoid multiple coupling along the view model layer
 *
 * @author David Mantilla
 */
public abstract class AbstractViewFacade {

    /**
     * Redirects to a page
     *
     * @param page
     * @param params
     */
    public abstract void navigate(Page page, Map<String, String> params);

    /**
     * Builds a full absolute url from a page and its parameters
     *
     * @param page
     * @param params
     * @return
     */
    public abstract String buildAbsoluteUrl(Page page, Map<String, String> params);

    /**
     * Get the current view parameters
     *
     * @return
     */
    public abstract Map<String, String> getViewParams();

    /**
     * Static getter for the current instance
     *
     * @return the curren view facade implementation
     */
    public static AbstractViewFacade getCurrentInstance() {
        // This method implementatios is highly coupled to the facade 
        // implementation, but this is THE ONLY place. Can be solved with a 
        // future dependency injection context
        return new ViewFacadeImpl();
    }

}
