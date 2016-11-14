package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.RangeOperand;

@Converter
public class RangeOperandConverter implements AttributeConverter<RangeOperand, String>{

    @Override
    public String convertToDatabaseColumn(RangeOperand rangeOperand) {
        return rangeOperand == null ? null : rangeOperand.name();
    }

    @Override
    public RangeOperand convertToEntityAttribute(String s) {
        return s == null ? null : RangeOperand.valueOf(s);
    }
}
