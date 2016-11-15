package com.hallocasa.utils.constants.propertyfieldparsing;

import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextIntegerConverter implements PropertyFieldValueConverter {

	@Override
	public PropertyFieldValueSpec toVo(String detail) {
		if(detail == null){
			return null;
		}
		PropertyFieldValueSpec spec = new PropertyFieldValueSpec();
		spec.setIntVal(Integer.parseInt(detail.trim()));
		return spec;
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		return (spec == null || spec.getIntVal() == null) 
				? null : String.valueOf(spec.getIntVal());
	}
}
