package com.hallocasa.utils.constants.parsing.flat;

import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.BASIC_DESCRIPTION_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.BATHROOMS_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.CURRENCY_PRES;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.IMAGE_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.LOC_DESCRIPTION_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.MARKET_PRICE_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.ROOMS_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.TITLE_PROP_FIELD_ID;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.FlatProperty;
import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValue;
import com.hallocasa.vo.properties.PropertyField;

/**
 * Specific parser to transform a Value Object property with field value list,
 * to a DTO Flat Property
 * @author Alexander Villamil
 */
public class FlatPropertyParser {
	
	/**
	 * Executes the transform process using constants attached
	 * @param property
	 * @param locale
	 */
	public FlatProperty transform(Property property, String locale) {
		FlatProperty flatProperty = new FlatProperty();
		for(PropertyField field : property.getFieldList()){
			if(field.getId().equals(TITLE_PROP_FIELD_ID)){
				flatProperty.setTitle(processByLocale(field.getFieldValueList(), locale));
			}
			if(field.getId().equals(BASIC_DESCRIPTION_PROP_FIELD_ID)){
				flatProperty.setBasicDescription(processByLocale(field.getFieldValueList(), locale));
			}
			if(field.getId().equals(LOC_DESCRIPTION_PROP_FIELD_ID)){
				flatProperty.setLocationDescription(processByLocale(field.getFieldValueList(), locale));
			}
			if(field.getId().equals(MARKET_PRICE_PROP_FIELD_ID)){
				flatProperty.setMarketPrice(processMarketPrice(field.getFieldValueList()));
			}
			if(field.getId().equals(IMAGE_PROP_FIELD_ID)){
				flatProperty.setUrlImage(processImage(field.getFieldValueList()));
			}
			if(field.getId().equals(ROOMS_PROP_FIELD_ID)){
				flatProperty.setRoomNumber(processRooms(field.getFieldValueList()));
			}
			if(field.getId().equals(BATHROOMS_PROP_FIELD_ID)){
				flatProperty.setBathNumber(processBathRooms(field.getFieldValueList()));
			}
		}
		return flatProperty;
	}

	private Integer processBathRooms(List<PropertyFieldValue> fieldValueList) {
		if(fieldValueList == null || fieldValueList.isEmpty()){
			return null;
		}
		return fieldValueList.get(0).getText().getIntVal();
	}

	private Integer processRooms(List<PropertyFieldValue> fieldValueList) {
		if(fieldValueList == null || fieldValueList.isEmpty()){
			return null;
		}
		return fieldValueList.get(0).getText().getIntVal();
	}

	private String processImage(List<PropertyFieldValue> fieldValueList) {
		if(fieldValueList == null || fieldValueList.isEmpty()){
			return null;
		}
		PropertyFieldValue selectedPropertyFieldValue = null;
		for(PropertyFieldValue fieldValue : fieldValueList){
			Boolean data2Entry = fieldValue.getData2().getBoolVal();
			if(data2Entry){
				selectedPropertyFieldValue = fieldValue;
			}
		}
		if(selectedPropertyFieldValue == null){
			return null;
		}
		return selectedPropertyFieldValue.getData1().getStrVal();
	}

	private String processMarketPrice(List<PropertyFieldValue> fieldValueList) {
		if(fieldValueList == null || fieldValueList.isEmpty()){
			return null;
		}
		return String.format(CURRENCY_PRES, fieldValueList.get(0).getData2().getDoubleVal());
	}

	private String processByLocale(List<PropertyFieldValue> fieldValueList, String locale) {
		Integer selectedIndex = 0;
		Integer currentIndex = 0;
		Integer localeEquivalent = FlatParserConstants.localeEquivalent(locale);
		for(PropertyFieldValue fieldValue : fieldValueList){
			Integer data1Entry = fieldValue.getData1().getIntVal();
			if(data1Entry == localeEquivalent){
				selectedIndex = currentIndex;
			}
			currentIndex++;
		}
		PropertyFieldValue fieldValue = fieldValueList.get(selectedIndex);
		return fieldValue.getData2().getStrVal();
	}
	
}
