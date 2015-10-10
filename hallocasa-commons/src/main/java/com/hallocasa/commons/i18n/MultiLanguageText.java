/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.i18n;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hallocasa.commons.Language;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author david
 */
public class MultiLanguageText implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Gson gson = new Gson();
    private String de;
    private String en;
    private String es;

    /**
     * Default constructor
     */
    public MultiLanguageText() {
    }

    /**
     * Constructor
     *
     * @param jsonText Create this object from a JSON text
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MultiLanguageText(String jsonText) {
        this.loadFromJSON(jsonText);
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
     * Converts this instance into a JSON text
     *
     * @return
     */
    public String toJSON() {
        return gson.toJson(this);
    }

    /**
     * Loads values from the jsonText given as parameter
     *
     * @param jsonText
     * @throws IllegalArgumentException when the JSON passed as argument is not
     * a valid JSON or doesn't match with expected JSON structure
     */
    public void loadFromJSON(String jsonText) {
        MultiLanguageText multiLanguageText = null;
        try {
            multiLanguageText = gson.fromJson(jsonText, MultiLanguageText.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException(e);
        }

        // avoid failing in empty string
        if (multiLanguageText == null) {
            multiLanguageText = new MultiLanguageText();
        }

        // copy converter object to this instance
        try {
            BeanUtils.copyProperties(this, multiLanguageText);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            // it shouldn't happen as the source object is the same destination object
            throw new RuntimeException("This shouldn't be happening ! end of word");
        }
    }

}
