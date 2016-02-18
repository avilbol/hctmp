/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.converters;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import javax.persistence.AttributeConverter;

/**
 *
 * @author David Mantilla
 * @param <T> Class of the attribute
 */
public abstract class JSONConverter<T> implements AttributeConverter<T, String> {

    private static final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(T x) {
        if (x == null) {
            return null;
        }
        return gson.toJson(x);
    }

    @Override
    public T convertToEntityAttribute(String y) {
        if (y == null) {
            return null;
        }
        return gson.fromJson(y, getAttributeType());
    }

    /**
     * Implementation of this method should return attribute class used to
     * conversion by gson.fromJson
     *
     * @return
     */
    public abstract Type getAttributeType();

}
