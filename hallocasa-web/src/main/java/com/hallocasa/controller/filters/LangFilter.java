/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.controller.filters;

import com.hallocasa.commons.Language;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.model.session.WebSessionImpl;
import java.io.IOException;
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

        // Initialize websession language
        WebSessionImpl webSession = (WebSessionImpl) WebSessionImpl.getCurrentInstance();
        if (initLanguageValue(webSession, lang, request)) {
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
     * Initialize language if it's not
     *
     * @return true if the language was got from the request parameter, false if
     * a default value or request locale was taken
     */
    private boolean initLanguageValue(WebSession webSession, String langParameter, ServletRequest servletRequest) {
        boolean isDefault = false;
        boolean isFromLocale = false;

        if (webSession.getCurrentLanguage() == null || langParameter != null) {
            Language language;

            // if there is not query string, locale is taken from request locale
            if (langParameter == null) {
                langParameter = servletRequest.getLocale().getLanguage();
                isFromLocale = true;
            }

            // try to obtain the locale from the lang parameter
            try {
                language = Language.valueOf(langParameter);
            } catch (IllegalArgumentException e) {
                language = null;
            }

            // if lang string value is not an existing language then english is taken
            if (language == null) {
                language = Language.en;
                isDefault = true;
            }

            webSession.changeLanguage(language);
        }
        return !(isDefault || isFromLocale);
    }

    /**
     * Appends the query String to the current url
     *
     * @param request
     * @return
     */
    private String appendLangQueryString(ServletRequest request) {
        WebSessionImpl webSession = (WebSessionImpl) WebSessionImpl.getCurrentInstance();

        // get the current language
        String strLang = webSession.getCurrentLanguage().name();
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
