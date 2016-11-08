package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class HcBooleanConverter implements AttributeConverter<Boolean, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Boolean boolValue) {
        return boolValue == null ? null : (boolValue ? 1 : 0);
    }

    @Override
    public Boolean convertToEntityAttribute(Integer intValue) {
        return intValue == null ? null : intValue.equals(1);
    }
}
