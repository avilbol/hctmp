/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.converters;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserTypeVO;
import com.hallocasa.dataentities.app.UserType;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.services.location.local.CountryServices;
import static com.hallocasa.view.utils.FormatUtils.isNumeric;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Alexander Villamil
 */
@FacesConverter(value = "userTypeConverter")
public class UserTypeConverter implements Converter {
    
    // Dependencies
    @Inject
    private HallocasaApplicationImpl halloCasaApplication;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !isNumeric(value)) {
            return null;
        }
        for(UserTypeVO userType : halloCasaApplication.getUserTypes()){
            if(userType.getId().equals(Long.parseLong(value))){
                return userType;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        return "" + ((UserTypeVO)value).getId();
    }
    
}
