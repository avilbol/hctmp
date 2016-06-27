package com.hallocasa.controller.filters;

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

import com.hallocasa.model.session.WebSession;
import com.hallocasa.model.session.WebSessionImpl;

/**
 * This class is intented to control the currency setting in the url. If the
 * crcy is not specified then this filter appends the current request currency to
 * the url; on the other hand, if the currency has been specified then this filter
 * change currency before response has been created
 *
 * @author Alexander Villamil
 */
public class CurrencyFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CurrencyFilter.class.getName());
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // this code only applies for GET requeset
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!httpServletRequest.getMethod().equals("GET") || httpServletRequest.getRequestURI().contains("javax.faces.resource")) {
            chain.doFilter(request, response);
            return;
        }

        // log url
        StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();

        // Get the currency parameter and change currency
        String crcy = request.getParameter("crcy");

        // Initialize websession currency
        WebSessionImpl webSession = (WebSessionImpl) WebSessionImpl.getCurrentInstance();
        if (initCurrencyValue(webSession, crcy, request)) {
            chain.doFilter(request, response);
        } // 
        // if no currency has been specified then user is redirect to the same
        // page but with specified query currency 
        else {
            // redirect to an url containing currency information
            String url = appendCrcyQueryString(request);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(url);
        }
    }

    /**
     * Initialize currency if it's not
     *
     * @return true if the currency was got from the request parameter, false if
     * a default value or request locale was taken
     */
    private boolean initCurrencyValue(WebSession webSession, String currencyParameter, ServletRequest servletRequest) {
        if (webSession.getCurrentCurrency() == null || currencyParameter != null) {
            webSession.changeCurrency(currencyParameter);
            return true;
        }
        return false;
    }

    /**
     * Appends the query String to the current url
     *
     * @param request
     * @return
     */
    private String appendCrcyQueryString(ServletRequest request) {
        WebSessionImpl webSession = (WebSessionImpl) WebSessionImpl.getCurrentInstance();

        // get the current currency
        String strCrcy = webSession.getCurrentCurrency().getAbbreviation();
        StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();
        String queryString = ((HttpServletRequest) request).getQueryString();

        if (queryString == null) {
            return requestURL + "?crcy=" + strCrcy;
        } else {
            return requestURL + "?" + queryString + "&crcy=" + strCrcy;
        }
    }

    @Override
    public void destroy() {
        //
    }

}
