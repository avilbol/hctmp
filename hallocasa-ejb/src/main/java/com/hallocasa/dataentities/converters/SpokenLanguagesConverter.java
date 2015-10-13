/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.converters;

import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.Language;
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
public class SpokenLanguagesConverter extends JSONConverter<ArrayList<Language>>
        implements AttributeConverter<ArrayList<Language>, String> {

    @Override
    public Type getAttributeType() {
        return new TypeToken<ArrayList<Language>>() {
        }.getType();
    }

}
