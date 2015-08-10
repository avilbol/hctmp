/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.servlet;

import com.hallocasa.commons.Language;
import com.hallocasa.model.session.WebSession;
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
import javax.servlet.http.HttpSession;

/**
 * This class is intented to control the language setting in the url. If the
 * lang is not specidifed then this filter appends the current request lang to
 * the url; on the other hand, if the lang has been specified then this filter
 * change language before response has been created
 *
 * @author David Mantilla
 */
public class LangFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LangFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // this code only applies for GET requeset
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!httpServletRequest.getMethod().equals("GET")) {
            chain.doFilter(request, response);
            return;
        }

        // log url
        StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();
  

        // Get the language parameter and change language
        String lang = request.getParameter("lang");

        // if an explicit language has been required then set the language in
        // session
        if (lang != null) {
            HttpSession session = httpServletRequest.getSession();
            WebSession webSession = (WebSession) session.getAttribute("webSession");
            if (webSession != null) {
                Language language;
                try {
                    language = Language.valueOf(lang);
                } catch (IllegalArgumentException e) {
                    language = Language.en;
                }
                webSession.changeLanguage(language);
            }else{
                LOG.log(Level.WARNING, "{0} has been invloked withot a session", requestURL.toString());
            }
            chain.doFilter(request, response);
        } // 
        // if no language has been specified then user is redirect to the same
        // page but with specified query language ( for social network sharing )
        else {
            // redirect to an url containing language information
            String url = appendLangQueryString(request);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(url);
        }
    }

    /**
     * Appends the query String to the current url
     *
     * @param request
     * @return
     */
    private String appendLangQueryString(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        WebSession webSession = (WebSession) session.getAttribute("webSession");
        String strLang;

        // get the current language
        if (webSession == null) {
            strLang = request.getLocale().getLanguage();
        } else {
            strLang = webSession.getCurrentLanguage().name();
        }

        StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();
        String queryString = ((HttpServletRequest) request).getQueryString();
        if (queryString == null) {
            return requestURL + "?lang=" + strLang;
        } else {
            return requestURL + "?" + queryString + "&lang=" + strLang;
        }
    }

    @Override
    public void destroy() {
        //
    }

}
