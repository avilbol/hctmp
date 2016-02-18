/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.converters;

import com.hallocasa.commons.vo.ImageContainer;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Alexander Villamil
 */
@Converter
public class ImageContainerConverter implements AttributeConverter<ImageContainer, String> {
	
    @Override
    public String convertToDatabaseColumn(ImageContainer value) {
        /*if(value == null){
            return null;
        }*/
        return "new value";
        //return value.getUrl();
    }

    @Override
    public ImageContainer convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return new ImageContainer(value);
    }
}
