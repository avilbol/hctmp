package com.hallocasa.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.hallocasa.vo.hcfilter.GroupTranslationManagement;


@Converter
public class GroupTranslationManagementConverter implements AttributeConverter<GroupTranslationManagement, String>{

	@Override
    public String convertToDatabaseColumn(GroupTranslationManagement filterOperationStep) {
        return filterOperationStep == null ? null : filterOperationStep.name();
    }

    @Override
    public GroupTranslationManagement convertToEntityAttribute(String s) {
        return s == null ? null : GroupTranslationManagement.valueOf(s);
    }
}
