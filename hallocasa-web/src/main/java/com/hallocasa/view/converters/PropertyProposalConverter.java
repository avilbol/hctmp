package com.hallocasa.view.converters;

import static com.hallocasa.view.utils.FormatUtils.isNumeric;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;

/**
 * Converter for @PropertyProposalVO
 * 
 * @author Alexander Villamil
 */
@FacesConverter(value = "propertyProposalConverter")
public class PropertyProposalConverter implements Converter {

	@Inject
	private HallocasaApplicationImpl halloCasaApplication;

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !isNumeric(value)) {
            return null;
        }
        for(PropertyProposalVO propertyProposalVO : halloCasaApplication.getPropertyProposals()){
            if(propertyProposalVO.getId().equals(Integer.parseInt(value))){
                return propertyProposalVO;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        return "" + ((PropertyProposalVO) value).getId();
    }

}
