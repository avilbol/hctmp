package com.hallocasa.view.converters;

import static com.hallocasa.view.utils.FormatUtils.isNumeric;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.model.application.HallocasaApplicationImpl;

/**
 * Converter for @CurrencyVO
 * 
 * @author Alexander Villamil
 */
@FacesConverter(value = "currencyConverter")
public class CurrencyConverter implements Converter{

	// Dependencies
    @Inject
    private HallocasaApplicationImpl halloCasaApplication;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !isNumeric(value)) {
            return null;
        }
        for(CurrencyVO currency : halloCasaApplication.getCurrencies()){
            if(currency.getId().equals(Long.parseLong(value))){
                return currency;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        return "" + ((CurrencyVO)value).getId();
    }

}
