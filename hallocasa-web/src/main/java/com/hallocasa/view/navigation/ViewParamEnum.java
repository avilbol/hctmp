/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.navigation;

/**
 *
 * @author david
 */
public enum ViewParamEnum {

    TOKEN("token"),
    EMAIL("email"),
    USER_ID("user-id");

    private final String paramKey;

    /**
     *
     * @param paramKey
     */
    private ViewParamEnum(String paramKey) {
        this.paramKey = paramKey;
    }

    /**
     * Getter for paramKey
     *
     * @return paramKey
     */
    public String getParamKey() {
        return paramKey;
    }

}
