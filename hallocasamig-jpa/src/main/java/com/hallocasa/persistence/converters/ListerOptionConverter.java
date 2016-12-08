package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.HcListerOption;

@Converter
public class ListerOptionConverter implements AttributeConverter<HcListerOption, String>{

	@Override
    public String convertToDatabaseColumn(HcListerOption listerOption) {
        return listerOption == null ? null : listerOption.name();
    }

    @Override
    public HcListerOption convertToEntityAttribute(String s) {
        return s == null ? null : HcListerOption.valueOf(s);
    }
}
