/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view;

import com.hallocasa.commons.constants.SystemConstants;

/**
 *
 * @author David Mantilla
 */
public enum ViewEnum {

    BLOG_INDEX("/pages/blog/index.xhtml"),
    LINKS("/pages/links/index.xhtml"),
    BUYING_PROCESS("/pages/buyprocess/index.xhtml");

    private final String url;

    private ViewEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getAbsoulteUrl() {
        return SystemConstants.APP_CONTEXT + url;
    }

}
