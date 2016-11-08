package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.HcFilterTypeNature;


@Converter
public class HcFilterTypeNatureConverter implements AttributeConverter<HcFilterTypeNature, String>{

    @Override
    public String convertToDatabaseColumn(HcFilterTypeNature filterTypeNature) {
        return filterTypeNature.name();
    }

    @Override
    public HcFilterTypeNature convertToEntityAttribute(String s) {
        return HcFilterTypeNature.valueOf(s);
    }
}
