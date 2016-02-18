/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author David Mantilla
 */
public class DoubleConverter implements Converter {

    @Override
    public Double getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return new Double(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if ( value == null ){
            return null;
        }
        return value.toString();
    }

}
