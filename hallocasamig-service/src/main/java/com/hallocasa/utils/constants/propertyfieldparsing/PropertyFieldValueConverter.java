package com.hallocasa.utils.constants.propertyfieldparsing;

import com.hallocasa.entities.properties.EntityPropertyFieldValue;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValue;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

/**
 * Interface for property field value converters
 * @author avillamil
 */
public interface PropertyFieldValueConverter {

	/**
	 * Transform the String property field value, to value object detail in its correct datatype
	 * @param ent
	 * @return
	 */
	PropertyFieldValueSpec toVo(String detail);
	
	/**
	 * Transform the property field value object detail in its correct String entity representation
	 * @param ent
	 * @param details
	 * @return
	 */
	String toEnt(PropertyFieldValueSpec spec, String... details);
	
}
