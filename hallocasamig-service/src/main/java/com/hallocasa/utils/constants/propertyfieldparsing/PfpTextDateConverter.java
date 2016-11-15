package com.hallocasa.utils.constants.propertyfieldparsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.hallocasa.utils.constants.exceptions.FatalException;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextDateConverter implements PropertyFieldValueConverter {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public PropertyFieldValueSpec toVo(String detail) {
		if(detail == null){
			return null;
		}
		try{
			PropertyFieldValueSpec spec = new PropertyFieldValueSpec();
			spec.setDateVal(sdf.parse(detail));
			return spec;
		} catch(ParseException e){
			throw new FatalException("Error inesperado", e);
		}
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		return (spec == null || spec.getDateVal() == null) ? null : sdf.format(spec.getDateVal());
	}
}
