package com.hallocasa.utils.resolvers;

import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextBooleanConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextDateConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextDatetimeConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextDoubleConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextFileConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PfpTextIntegerConverter;
import com.hallocasa.utils.constants.propertyfieldparsing.PropertyFieldValueConverter;
import com.hallocasa.vo.hcfilter.properties.PropertyDatatype;

public enum PropertyDataTypeRes {
	SAME(new PfpTextConverter(), false), 
	INT(new PfpTextIntegerConverter(), false), 
	DOUBLE(new PfpTextDoubleConverter(), false), 
	BOOLEAN(new PfpTextBooleanConverter(), false), 
	TEXT(new PfpTextConverter(), false), 
	DATE(new PfpTextDateConverter(), false), 
	DATETIME(new PfpTextDatetimeConverter(), false), 
	FILE(new PfpTextFileConverter(), true);

	private PropertyFieldValueConverter converter;

	private Boolean requiresExtraParameters;

	private PropertyDataTypeRes(PropertyFieldValueConverter converter, Boolean requiresExtraParameters) {
		this.converter = converter;
		this.requiresExtraParameters = requiresExtraParameters;
	}

	public static PropertyFieldValueConverter getConverter(PropertyDatatype propertyDatatype) {
		return PropertyDataTypeRes.valueOf(propertyDatatype.name()).converter;
	}

	public static Boolean getRequiresExtraParameters(PropertyDatatype propertyDatatype) {
		return PropertyDataTypeRes.valueOf(propertyDatatype.name()).requiresExtraParameters;
	}
}
