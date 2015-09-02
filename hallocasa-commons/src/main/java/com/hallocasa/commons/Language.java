/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons;

import java.util.Locale;

/**
 *
 * @author David Mantilla
 */
public enum Language {

    en("English"), es("Español"), de("Deutsch");

    private final Locale locale;
    private final String languageName;

    private Language(String languageName) {
        locale = new Locale(this.name());
        this.languageName = languageName;
    }

    /**
     * Return the language Name
     *
     * @return
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }
}
