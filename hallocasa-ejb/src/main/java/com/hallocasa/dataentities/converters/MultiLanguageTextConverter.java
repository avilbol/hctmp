/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.converters;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.dataentities.types.LanguageList;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author david
 */
@Converter
public class MultiLanguageTextConverter implements AttributeConverter<MultiLanguageText, String> {

	public MultiLanguageTextConverter() {
        super();
    }
	
    @Override
    public String convertToDatabaseColumn(MultiLanguageText value) {
        if (value == null) {
            return null;
        }
        return value.toJSON();
    }

    @Override
    public MultiLanguageText convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return new MultiLanguageText(value);
    }

}
