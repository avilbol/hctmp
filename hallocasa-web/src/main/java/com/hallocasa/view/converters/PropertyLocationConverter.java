package com.hallocasa.view.converters;

import static com.hallocasa.view.utils.FormatUtils.isNumeric;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;

/**
 * Converter for @PropertyLocationVO
 * 
 * @author Alexander Villamil
 */
@FacesConverter(value = "propertyLocationConverter")
public class PropertyLocationConverter implements Converter {

	@Inject
	private HallocasaApplicationImpl halloCasaApplication;

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !isNumeric(value)) {
            return null;
        }
        for(PropertyLocationVO propertyLocationVO : halloCasaApplication.getPropertyLocations()){
            if(propertyLocationVO.getId().equals(Integer.parseInt(value))){
                return propertyLocationVO;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        return "" + ((PropertyLocationVO) value).getId();
    }

}
