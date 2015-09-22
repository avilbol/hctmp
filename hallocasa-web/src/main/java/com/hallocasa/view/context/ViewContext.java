/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.context;

/**
 *
 * @author david
 */
public interface ViewContext {

    /**
     * Shows a global error message
     *
     * @param summaryKey
     * @param detailKey Key for detail message. If no detail message is wanted,
     * then null can be sent
     */
    public void showGlobalErrorMessage(String summaryKey, String detailKey);

    /**
     * Adds a callback parameter
     *
     * @param name
     * @param value
     */
    public void addCallBackParam(String name, Object value);

    /**
     * Shows a global error message using a different bundle
     *
     * @param summary
     * @param detail
     */
    public void showGlobalCustomErrorMessage(String summary, String detail);
}
