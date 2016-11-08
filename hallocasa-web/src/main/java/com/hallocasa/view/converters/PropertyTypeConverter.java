package com.hallocasa.view.converters;

import static com.hallocasa.view.utils.FormatUtils.isNumeric;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.hallocasa.commons.vo.properties.PropertyTypeVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;

/**
 * Converter for @PropertyTypeVO
 * 
 * @author Alexander Villamil
 */
@FacesConverter(value = "propertyTypeConverter")
public class PropertyTypeConverter implements Converter {

	@Inject
	private HallocasaApplicationImpl halloCasaApplication;

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !isNumeric(value)) {
            return null;
        }
        for(PropertyTypeVO propertyTypeVO : halloCasaApplication.getPropertyTypes()){
            if(propertyTypeVO.getId().equals(Integer.parseInt(value))){
                return propertyTypeVO;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        return "" + ((PropertyTypeVO) value).getId();
    }

}
