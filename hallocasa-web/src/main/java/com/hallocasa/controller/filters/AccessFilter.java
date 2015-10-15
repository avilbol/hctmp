/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.controller.filters;

import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.view.navigation.HallocasaViewEnum;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter is intended to control access to pages to prevent required
 * authorization page is accessed without user logged session
 *
 * @author David Mantilla
 */
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();
        HallocasaViewEnum viewEnum;

        // if is a resource skip filter
        String url = requestURL.toString().replaceAll(SystemConstants.SERVER_URL, "");
        if (!isResource(url) && (!isDefaultView(url))) {
            // look for the page in the enum
            try {
                viewEnum = HallocasaViewEnum.findByUrl(url);
            } catch (IllegalArgumentException e) {
                redirectTo(response, HallocasaViewEnum.PAGE_NOT_FOUND);
                return;
            }

            // check if the view requires logged session
            if (viewEnum.isRequiresLogin()) {
                if ((WebSessionImpl.getCurrent() == null)
                        || (!WebSessionImpl.getCurrent().isLogged())) {
                    redirectTo(response, HallocasaViewEnum.FORBIDDEN);
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //
    }

    /**
     * Redirects to a Hallocasa view
     *
     * @param response
     * @param hallocasaViewEnum
     */
    private void redirectTo(ServletResponse response,
            HallocasaViewEnum hallocasaViewEnum) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            httpResponse.sendRedirect(hallocasaViewEnum.getUrl());
        } catch (IOException ex) {
            Logger.getLogger(AccessFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Evaluates if the URL is a resource
     *
     * @param url
     * @return
     */
    private boolean isResource(String url) {
        return url.startsWith("/javax.faces.resource");
    }

    /**
     * Evaluates if the URL is the default view
     *
     * @param url
     * @return
     */
    private boolean isDefaultView(String url) {
        return url.equals("/") || (url.equals(HallocasaViewEnum.DEFAULT_VIEW.getUrl()));
    }

}
