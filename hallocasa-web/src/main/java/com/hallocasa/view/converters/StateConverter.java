/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.converters;

import com.hallocasa.commons.vo.StateVO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import static com.hallocasa.view.utils.FormatUtils.*;

/**
 *
 * @author Alexander Villamil
 */
@FacesConverter(value = "stateConverter")
public class StateConverter implements Converter {

   
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (isNumeric(value)) {
            return new StateVO(Long.parseLong(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if(value == null){
    		return null;
    	}
        return "" + ((StateVO) value).getId();
    }
}
