/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.model.session;

import com.hallocasa.commons.Language;
import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author David Mantilla
 */
@ManagedBean(name = "webSession")
@SessionScoped
public class WebSession implements Serializable {

    private Language currentLanguage;

    //private OneForSession oneForSession;
    /**
     * Post construct
     */
    @PostConstruct
    public void initialize() {
        getCurrentLanguage();
    }

    /**
     * Returns the current language
     *
     * @return
     */
    public Language getCurrentLanguage() {
        if (currentLanguage == null) {
            try {
                String langParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lang");
                if (langParameter != null) {
                    currentLanguage = Language.valueOf(langParameter);
                } else {
                    currentLanguage = Language.valueOf(FacesContext.getCurrentInstance()
                            .getViewRoot().getLocale().getLanguage());
                }
            } catch (IllegalArgumentException e) {
                changeLanguage(Language.en);
                return Language.en;
            }
        }
        return currentLanguage;
    }

    /**
     * Change language
     *
     * @param language
     */
    public void changeLanguage(Language language) {
        currentLanguage = language;
    }

    /**
     * Return the current instance of the webSession
     *
     * @return
     */
    public static WebSession getCurrentInstance() {
        return FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(
                FacesContext.getCurrentInstance(), "#{webSession}", WebSession.class);
    }
}
