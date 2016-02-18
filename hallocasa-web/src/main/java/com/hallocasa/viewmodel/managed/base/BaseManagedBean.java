/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.base;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.hallocasa.view.utils.JSFUtils;

/**
 *
 * @author David Mantilla
 */
public class BaseManagedBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7435406346992303687L;

    /**
     *
     * @param facesMessage
     */
    @Deprecated
    public void addFacesMessage(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    /**
     *
     * @param id
     * @param severity
     * @param summary
     * @param detail
     * @deprecated Use view facade methods for showing messages
     */
    @Deprecated
    public void addFacesMessage(String id, FacesMessage.Severity severity,
            String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(id, facesMessage);

    }

    /**
     * Return a message from the view bundle
     *
     * @param resourceBundleKey
     * @return
     */
    @Deprecated
    public String getBundleMessage(String resourceBundleKey) {
        return JSFUtils.getViewBundleString(resourceBundleKey);
    }
    

}
