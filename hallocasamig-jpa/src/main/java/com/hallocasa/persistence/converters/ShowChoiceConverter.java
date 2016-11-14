package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.ShowChoice;

@Converter
public class ShowChoiceConverter implements AttributeConverter<ShowChoice, String>{

    @Override
    public String convertToDatabaseColumn(ShowChoice showChoice) {
        return showChoice == null ? null : showChoice.name();
    }

    @Override
    public ShowChoice convertToEntityAttribute(String s) {
        return s == null ? null : ShowChoice.valueOf(s);
    }
}
