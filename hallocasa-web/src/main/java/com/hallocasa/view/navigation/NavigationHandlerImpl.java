/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.navigation;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.view.exceptions.PageNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author David Mantilla
 */
@Named(value = "NavigationHandler")
@SessionScoped
public class NavigationHandlerImpl implements Serializable, NavigationHandler {

    private final static Logger LOG = Logger.getLogger(NavigationHandlerImpl.class.getName());
    private static final Map<HallocasaViewEnum, String> PAGES_MAP;

    /* constructors */
    static {
        // maps a url for all pages
        PAGES_MAP = new HashMap<>();
        PAGES_MAP.put(HallocasaViewEnum.HOME, "/facelets/pages/main.xhtml");
        PAGES_MAP.put(HallocasaViewEnum.BLOG_INDEX, "/pages/blog/index.xhtml");
        PAGES_MAP.put(HallocasaViewEnum.BLOG_ARTICLE, "/pages/blog/article.xhtml");
    }

    /**
     * Default constructor
     */
    public NavigationHandlerImpl() {

    }

    /**
     * Redirect to page
     *
     * @param view Enum with page info
     */
    @Override
    public void redirectToPage(HallocasaViewEnum view) {
        String url = PAGES_MAP.get(view);
        if (url == null) {
            throw new PageNotFoundException(view + "is not maped in the pages map");
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
     * @param view
     * @param params
     */
    @Override
    public void redirectToPage(HallocasaViewEnum view, Map<String, String> params) {
        String url = PAGES_MAP.get(view);
        if (url == null) {
            throw new PageNotFoundException(view + "is not maped in the pages map");
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
     *
     * @param view
     * @param params
     * @return
     */
    @Override
    public String buildAbsoluteUrl(HallocasaViewEnum view, Map<String, String> params) {
        String pageUrl = PAGES_MAP.get(view);
        if (pageUrl == null) {
            throw new PageNotFoundException(view + "is not maped in the pages map");
        }

        StringBuilder str = new StringBuilder();
        str.append(SystemConstants.SERVER_URL);
        str.append(SystemConstants.APP_CONTEXT);
        str.append(pageUrl);
        if (params != null && !params.isEmpty()) {
            str.append("?");
            boolean first = true;
            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                if (!first) {
                    str.append("&");
                }
                // validates page support parameter
                if (!ArrayUtils.contains(view.getSupportedParams(),
                        ViewParamEnum.find(paramEntry.getKey()))) {
                    throw new IllegalArgumentException("View " + view
                            + " doesn't support param " + paramEntry.getKey());
                }

                str.append(paramEntry.getKey());
                str.append("=");
                str.append(paramEntry.getValue());
                first = false;
            }
        }
        return str.toString();
    }

    @Override
    public Map<String, String> getPageParams() {
        if (FacesContext.getCurrentInstance() != null) {
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        } else {
            return new HashMap<>();
        }
    }

}
