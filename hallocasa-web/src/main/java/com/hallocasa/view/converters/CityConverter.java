/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.converters;

import com.hallocasa.commons.vo.CityVO;
import static com.hallocasa.view.utils.FormatUtils.isNumeric;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alexander Villamil
 */
@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter{
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (isNumeric(value)) {
            return new CityVO(Long.parseLong(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if(value == null){
    		return null;
    	}
        return "" + ((CityVO) value).getId();
    }
    
}
