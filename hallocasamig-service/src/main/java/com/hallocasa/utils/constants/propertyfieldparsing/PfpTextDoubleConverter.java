package com.hallocasa.utils.constants.propertyfieldparsing;

import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextDoubleConverter implements PropertyFieldValueConverter {

	@Override
	public PropertyFieldValueSpec toVo(String detail) {
		if(detail == null){
			return null;
		}
		PropertyFieldValueSpec spec = new PropertyFieldValueSpec();
		spec.setDoubleVal(Double.parseDouble(detail.trim()));
		return spec;
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		return (spec == null || spec.getDoubleVal() == null) 
				? null : String.valueOf(spec.getDoubleVal());
	}
}
