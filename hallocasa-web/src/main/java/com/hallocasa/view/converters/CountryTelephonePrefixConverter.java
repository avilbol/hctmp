package com.hallocasa.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.hallocasa.commons.vo.CountryTelephonePrefix;

/**
 * Converter for country telephone prefix
 * @author Alexander Villamil
 */
@FacesConverter(value = "countryTelephonePrefixConverter")
public class CountryTelephonePrefixConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(arg2 == null){
			return null;
		}
		return new CountryTelephonePrefix(Long.parseLong(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 == null){
			return null;
		}
		return "" + ((CountryTelephonePrefix)arg2).getId();
	}

}
