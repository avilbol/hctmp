package com.hallocasa.utils.constants.parsing.flat;

import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.BASIC_DESCRIPTION_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.BATHROOMS_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.CURRENCY_PRES;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.FOUND_KEY;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.IMAGE_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.LOC_DESCRIPTION_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.MARKET_PRICE_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.ROOMS_PROP_FIELD_ID;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.TEXT_KEY;
import static com.hallocasa.utils.constants.parsing.flat.FlatParserConstants.TITLE_PROP_FIELD_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @param standardLocaleList
	 */
	public FlatProperty transform(Property property, List<String> standardLocaleList) {
		FlatProperty flatProperty = new FlatProperty();
		for(PropertyField field : property.getFieldList()){
			if(field.getId().equals(TITLE_PROP_FIELD_ID)){
				flatProperty.setTitle(processByLocaleAlternates(field.getFieldValueList(), standardLocaleList));
			}
			if(field.getId().equals(BASIC_DESCRIPTION_PROP_FIELD_ID)){
				flatProperty.setBasicDescription(processByLocaleAlternates(field.getFieldValueList(), standardLocaleList));
			}
			if(field.getId().equals(LOC_DESCRIPTION_PROP_FIELD_ID)){
				flatProperty.setLocationDescription(processByLocaleAlternates(field.getFieldValueList(), standardLocaleList));
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
	
	/**
	 * Establish the adequate user description to show, according to a locale alternates
	 * @param fieldValueList
	 * 		the list of candidate field value list entries to show
	 * @param locale alternates
	 * 		the locale alternates tan can match a user description
	 * @return
	 * 		the user description to show (translation - applied)
	 */
	private String processByLocaleAlternates(List<PropertyFieldValue> fieldValueList, List<String> standardLocaleList) {
		Map<String, String> result = null;
		for(String locale : standardLocaleList){
			result = processByLocale(fieldValueList, locale);
			if(Boolean.valueOf(result.get(FOUND_KEY))){
				return result.get(TEXT_KEY);
			}
		}
		return result.get(TEXT_KEY);
	}

	/**
	 * Establish the adequate user description to show, according to a locale
	 * @param descriptionList
	 * 		the list of candidate user descriptions to show
	 * @param locale
	 * 		the locale to use as selector
	 * @return
	 * 		the user description to show (translation - applied) and a flag specifying if the locale match
	 * 		any user description
	 */
	private Map<String,String> processByLocale(List<PropertyFieldValue> fieldValueList, String locale) {
		Integer selectedIndex = 0;
		Integer currentIndex = 0;
		boolean foundLanguageEntry = false;
		Integer localeEquivalent = FlatParserConstants.localeEquivalent(locale);
		for(PropertyFieldValue fieldValue : fieldValueList){
			Integer data1Entry = fieldValue.getData1().getIntVal();
			if(data1Entry == localeEquivalent){
				selectedIndex = currentIndex;
				foundLanguageEntry = true;
			}
			currentIndex++;
		}
		PropertyFieldValue fieldValue = fieldValueList.get(selectedIndex);
		Map<String, String> result = new HashMap<>();
		result.put(TEXT_KEY, fieldValue.getData2().getStrVal());
		result.put(FOUND_KEY, String.valueOf(foundLanguageEntry));
		return result;
	}
	
}
