package com.hallocasa.dataentities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter for database entries Y/N
 * @author avillamil
 *
 */
@Converter
public class DatabaseBooleanConverter implements AttributeConverter<Boolean, String>{

	@Override
	public String convertToDatabaseColumn(Boolean arg0) {
		if(arg0 == null){
			return "N";
		}
		return arg0 ? "Y" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String arg0) {
		if(arg0 == null){
			return false;
		}
		return arg0.equals("Y");
	}

}
