package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.RangeOperand;

@Converter
public class RangeOperandConverter implements AttributeConverter<RangeOperand, String>{

    @Override
    public String convertToDatabaseColumn(RangeOperand rangeOperand) {
        return rangeOperand.name();
    }

    @Override
    public RangeOperand convertToEntityAttribute(String s) {
        return RangeOperand.valueOf(s);
    }
}
