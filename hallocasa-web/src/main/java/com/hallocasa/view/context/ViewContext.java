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
     * @param detailKey
     */
    public void showGlobalErrorMessage(String summaryKey, String detailKey);
}
