/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.controller.filters;

import com.hallocasa.commons.constants.SystemConstants;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author David Mantilla
 */
public class ServerUrlFinder implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // this code only applies for GET requeset
        if (SystemConstants.SERVER_URL == null) {
            HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
            int slahsIndex = request.getRequestURL().indexOf("/", 9);
            if (slahsIndex == - 1) {
                SystemConstants.SERVER_URL = request.getRequestURL().toString();
            } else {
                SystemConstants.SERVER_URL = request.getRequestURL().substring(0, slahsIndex);
            }
        }
    }
}
