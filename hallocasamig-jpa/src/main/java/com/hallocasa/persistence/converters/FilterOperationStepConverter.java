package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.FilterOperationStep;


@Converter
public class FilterOperationStepConverter implements AttributeConverter<FilterOperationStep, String>{

	@Override
    public String convertToDatabaseColumn(FilterOperationStep filterOperationStep) {
        return filterOperationStep == null ? null : filterOperationStep.name();
    }

    @Override
    public FilterOperationStep convertToEntityAttribute(String s) {
        return FilterOperationStep.valueOf(s);
    }
}
