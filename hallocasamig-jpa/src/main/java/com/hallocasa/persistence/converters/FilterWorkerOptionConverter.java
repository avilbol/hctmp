package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.FilterWorkerOption;

@Converter
public class FilterWorkerOptionConverter implements AttributeConverter<FilterWorkerOption, String>{

	@Override
    public String convertToDatabaseColumn(FilterWorkerOption filterWorkerOption) {
        return filterWorkerOption == null ? null : filterWorkerOption.name();
    }

    @Override
    public FilterWorkerOption convertToEntityAttribute(String s) {
        return s == null ? null : FilterWorkerOption.valueOf(s);
    }
}