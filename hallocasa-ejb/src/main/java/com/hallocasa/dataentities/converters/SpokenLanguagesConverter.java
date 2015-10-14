/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.Language;
import com.hallocasa.dataentities.types.LanguageList;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter from SpokenLanguage attribute
 *
 * @author David Mantilla
 */
@Converter
public class SpokenLanguagesConverter
        implements AttributeConverter<LanguageList, String> {

    private static final Gson gson = new Gson();

    public SpokenLanguagesConverter() {
        super();
    }

    public Type getAttributeType() {
        return new TypeToken<LanguageList>() {
        }.getType();
    }

    @Override
    public String convertToDatabaseColumn(LanguageList x) {
        if (x == null) {
            return null;
        }
        return gson.toJson(x);
    }

    @Override
    public LanguageList convertToEntityAttribute(String y) {
        if (y == null) {
            return null;
        }
        return gson.fromJson(y, getAttributeType());
    }

}
