package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.RangeFieldPresentation;

@Converter
public class RangeFieldPresentationConverter implements AttributeConverter<RangeFieldPresentation, String>{

    @Override
    public String convertToDatabaseColumn(RangeFieldPresentation filterTypeNature) {
        return filterTypeNature.name();
    }

    @Override
    public RangeFieldPresentation convertToEntityAttribute(String s) {
        return RangeFieldPresentation.valueOf(s);
    }
}
