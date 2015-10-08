/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.i18n;

import com.hallocasa.commons.Language;
import java.io.Serializable;

/**
 *
 * @author david
 */
public class MultiLanguageText implements Serializable {

    private static final long serialVersionUID = 1L;
    private String en;
    private String es;
    private String de;

    /**
     * Constructor
     */
    public MultiLanguageText() {
    }

    public String getText(Language language) {
        switch (language) {
            case de:
                return getDe();
            case en:
                return getEn();
            case es:
                return getEs();
            default:
                throw new UnsupportedOperationException("Language " + language + " not supported yet");
        }
    }

    /**
     * @return the en
     */
    public String getEn() {
        return en;
    }

    /**
     * @param en the en to set
     */
    public void setEn(String en) {
        this.en = en;
    }

    /**
     * @return the es
     */
    public String getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(String es) {
        this.es = es;
    }

    /**
     * @return the de
     */
    public String getDe() {
        return de;
    }

    /**
     * @param de the de to set
     */
    public void setDe(String de) {
        this.de = de;
    }

}
