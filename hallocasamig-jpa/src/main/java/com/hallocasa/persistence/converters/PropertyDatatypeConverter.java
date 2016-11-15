package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.utils.constants.propertyfieldparsing.PropertyDatatype;

@Converter
public class PropertyDatatypeConverter implements AttributeConverter<PropertyDatatype, String>{

    @Override
    public String convertToDatabaseColumn(PropertyDatatype propertyDatatype) {
        return propertyDatatype == null ? null : propertyDatatype.name();
    }

    @Override
    public PropertyDatatype convertToEntityAttribute(String dbValue) {
        return dbValue == null ? PropertyDatatype.SAME : PropertyDatatype.valueOf(dbValue);
    }
}
