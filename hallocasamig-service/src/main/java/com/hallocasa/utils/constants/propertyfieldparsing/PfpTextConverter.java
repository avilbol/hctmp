package com.hallocasa.utils.constants.propertyfieldparsing;

import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextConverter implements PropertyFieldValueConverter {

	@Override
	public PropertyFieldValueSpec toVo(String detail) {
		if(detail == null){
			return null;
		}
		PropertyFieldValueSpec spec = new PropertyFieldValueSpec();
		spec.setStrVal(detail.trim());
		return spec;
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		return (spec == null || spec.getStrVal() == null) ? null : spec.getStrVal().trim();
	}
}
