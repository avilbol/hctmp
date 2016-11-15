package com.hallocasa.vo.hcfilter.properties;

public enum PropertyDatatype {
	SAME(new PfpTextConverter(), false),
	INT(new PfpTextIntegerConverter(), false),
	DOUBLE(new PfpTextDoubleConverter(), false),
	BOOLEAN(new PfpTextBooleanConverter(), false),
	TEXT(new PfpTextConverter(), false),
	DATE(new PfpTextDateConverter(), false),
	DATETIME(new PfpTextDatetimeConverter(), false),
	FILE(new PfpTextFileConverter(), false);
	
	private PropertyFieldValueConverter converter;
	
	private Boolean requiresExtraParameters;

	private PropertyDatatype(PropertyFieldValueConverter converter, Boolean requiresExtraParameters) {
		this.converter = converter;
		this.requiresExtraParameters = requiresExtraParameters;
	}

	public PropertyFieldValueConverter getConverter() {
		return converter;
	}

	public void setConverter(PropertyFieldValueConverter converter) {
		this.converter = converter;
	}

	public Boolean getRequiresExtraParameters() {
		return requiresExtraParameters;
	}

	public void setRequiresExtraParameters(Boolean requiresExtraParameters) {
		this.requiresExtraParameters = requiresExtraParameters;
	}
}
