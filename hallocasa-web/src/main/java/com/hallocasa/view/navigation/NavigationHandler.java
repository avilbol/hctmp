/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.navigation;

import java.util.Map;

/**
 *
 * @author david
 */
public interface NavigationHandler {

    /**
     * Builds absolute url given the page enum and the params
     *
     * @param view
     * @param params
     * @return
     */
    public String buildAbsoluteUrl(HallocasaViewEnum view, Map<String, String> params);

    /**
     * Redirect to page
     *
     * @param view Enum with page info
     */
    public void redirectToPage(HallocasaViewEnum view);

    /**
     * Redirects to a page
     *
     * @param view
     * @param params
     */
    public void redirectToPage(HallocasaViewEnum view, Map<String, String> params);

    /**
     * Return the map with request parameters
     * @return 
     */
    public Map<String, String> getPageParams();

}
