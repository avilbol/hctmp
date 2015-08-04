/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.navigation;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.view.exceptions.PageNotFoundException;
import com.hallocasa.viewmodel.viewfacade.Page;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author David Mantilla
 */
public class NavigationHandler {

    private final static Logger LOG = Logger.getLogger(NavigationHandler.class.getName());
    private static final Map<Page, String> PAGES_MAP;

    /* constructors */
    static {
        // maps a url for all pages
        PAGES_MAP = new HashMap<>();
        PAGES_MAP.put(Page.HOME, "/facelets/pages/main.xhtml");
        PAGES_MAP.put(Page.PROPERTIES_LIST, "/pages/properties/main.xhtml");
        PAGES_MAP.put(Page.BLOG_INDEX, "/pages/blog/index.xhtml");
        PAGES_MAP.put(Page.BLOG_ARTICLE, "/pages/blog/article.xhtml");
    }

    /**
     * Default constructor
     */
    public NavigationHandler() {

    }

    /**
     * Redirect to page
     *
     * @param page Enum with page info
     */
    public void redirectToPage(Page page) {
        String url = PAGES_MAP.get(page);
        if (url == null) {
            throw new PageNotFoundException(page + "is not maped in the pages map");
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    buildViewUrl(url, null));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PageNotFoundException("Page doesn't exist in the project");
        }
    }

    /**
     * Redirects to a page
     *
     * @param page
     * @param params
     */
    public void redirectToPage(Page page, Map<String, String> params) {
        String url = PAGES_MAP.get(page);
        if (url == null) {
            throw new PageNotFoundException(page + "is not maped in the pages map");
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    buildViewUrl(url, params));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PageNotFoundException("Page doesn't exist in the project");
        }
    }

    /**
     * Adjust a page file name to be a valid faces redirection URL
     *
     * @param pageFileName
     * @param queryStrings
     * @return Url lista para ser redireccionada
     */
    private String buildViewUrl(String pageFileName,
            Map<String, String> queryStrings) {

        // construye la dirección
        StringBuilder str = new StringBuilder();

        // application and faces full context path
        str.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        str.append(pageFileName);

        if (queryStrings != null) {
            str.append("?");
            for (String key : queryStrings.keySet()) {
                str.append(key).append("=").append(queryStrings.get(key));
            }
        }

        return str.toString();
    }

    /**
     * Builds absolute url given the page enum and the params
     * @param page
     * @param params
     * @return 
     */
    public String buildAbsoluteUrl(Page page, Map<String, String> params) {
        String pageUrl = PAGES_MAP.get(page);
        if (pageUrl == null) {
            throw new PageNotFoundException(page + "is not maped in the pages map");
        }
        
       StringBuilder str = new StringBuilder();
       str.append( SystemConstants.SERVER_URL );
       str.append( SystemConstants.APP_CONTEXT );
       str.append( pageUrl );
       if ( params != null && !params.isEmpty() ){
           str.append("?");
           boolean first = true;
           for ( Map.Entry<String,String> paramEntry : params.entrySet() ){
               if ( !first ){
                   str.append( "&");
               }
               str.append( paramEntry.getKey() );
               str.append( "=");
               str.append( paramEntry.getValue() );
               first = false;
           }
       }
       return str.toString();
    }

}
