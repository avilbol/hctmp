/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.converters;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.services.location.local.CountryServices;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import static com.hallocasa.view.utils.FormatUtils.*;

/**
 *
 * @author Alexander Villamil
 */
@FacesConverter(value = "countryConverter")
public class CountryConverter implements Converter {

    // Dependencies
    @EJB
    private CountryServices countryServices;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !isNumeric(value)) {
            return null;
        }
        for(CountryVO country : countryServices.getCountries()){
            if(country.getId().equals(Long.parseLong(value))){
                return country;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        return "" + ((CountryVO) value).getId();
    }
}
