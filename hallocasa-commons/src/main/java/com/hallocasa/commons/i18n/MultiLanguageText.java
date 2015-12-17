/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.i18n;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.Language;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author david
 */
public class MultiLanguageText implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Gson gson = new Gson();
    private Map<Language, String> langMap;

    /**
     * Default constructor
     */
    public MultiLanguageText() {
        langMap = new LinkedHashMap<>();
    }

    public MultiLanguageText(MultiLanguageText another) {
        this.langMap = another.langMap;
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
        String languageValue = this.langMap.get(language);
        if(languageValue == null){
            throw new UnsupportedOperationException("Language " + language + " not supported yet");
        }
        return languageValue;
    }

    public Map<Language, String> getLangMap() {
        return langMap;
    }

    public void setLangMap(Map<Language, String> langMap) {
        this.langMap = langMap;
    }
    
    public String getLangValue(Language langValue){
        return langMap.get(langValue);
    }
    
    public void setLangValue(Language langValue, String value){
        if(langMap == null){
            langMap = new HashMap<>();
        }
        langMap.put(langValue, value);
    }

    /**
     * Converts this instance into a JSON text
     *
     * @return
     */
    public String toJSON() {
        return gson.toJson(this.langMap);
    }

    /**
     * Loads values from the jsonText given as parameter
     *
     * @param jsonText
     * @throws IllegalArgumentException when the JSON passed as argument is not
     * a valid JSON or doesn't match with expected JSON structure
     */
    public void loadFromJSON(String jsonText) {
        MultiLanguageText multiLanguageText = new MultiLanguageText();
        try {
            if(!jsonText.isEmpty()){
                Type type = new TypeToken<Map<Language, String>>(){}.getType();
                multiLanguageText.setLangMap(
                    (Map<Language, String>) gson.fromJson(jsonText, type));
            }
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException(e);
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
