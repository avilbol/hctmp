/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.navigation;

import java.util.HashMap;
import java.util.Map;

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

    /* static */
    private static final Map<String, ViewParamEnum> map;

    static {
        map = new HashMap<>();
        for (ViewParamEnum vp : ViewParamEnum.values()) {
            map.put(vp.getParamKey(), vp);
        }
    }

    /**
     *
     * @param paramKey
     * @return found
     */
    public static ViewParamEnum find(String paramKey) {
        ViewParamEnum found = map.get(paramKey);
        if (found == null) {
            throw new IllegalArgumentException(paramKey);
        }
        return found;
    }

}
