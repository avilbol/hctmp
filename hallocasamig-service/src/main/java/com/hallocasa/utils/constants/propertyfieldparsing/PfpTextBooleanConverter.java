package com.hallocasa.utils.constants.propertyfieldparsing;

import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextBooleanConverter implements PropertyFieldValueConverter {

	@Override
	public PropertyFieldValueSpec toVo(String detail) {
		if(detail == null){
			return null;
		}
		PropertyFieldValueSpec spec = new PropertyFieldValueSpec();
		spec.setBoolVal(detail.trim().equals("1"));
		return spec;
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		return (spec == null || spec.getBoolVal() == null) ? null : (spec.getBoolVal() ? "1" : "0");
	}
}
