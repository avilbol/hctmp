package com.hallocasa.view.converters;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.hallocasa.commons.vo.Coordinate;

/**
*
* @author Alexander Villamil
*/
@FacesConverter(value = "coordinateConverter")
public class CoordinateConverter implements Converter{
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("null") || value.trim().isEmpty()){
        	return null;
        }
        return new Coordinate(new BigDecimal(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if(value == null){
    		return "";
    	}
    	return ((Coordinate)value).getDecRepresentation().toString();
    }
    
}

