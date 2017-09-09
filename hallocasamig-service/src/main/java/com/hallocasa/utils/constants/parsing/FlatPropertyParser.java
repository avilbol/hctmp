package com.hallocasa.utils.constants.parsing;

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

	private static final Integer EN_ID = 1;
	private static final Integer ES_ID = 2;
	private static final Integer DE_ID = 3;
	private static final Integer TITLE_PF_ID = 2;
	private static final Integer BASIC_DESCRIPTION_PF_ID = 3;
	private static final Integer LOC_DESCRIPTION_PF_ID = 4;
	private static final Integer MARKET_PRICE_PF_ID = 5;
	private static final Integer IMAGE_PF_ID = 12;
	private static final Integer ROOMS_PF_ID = 17;
	private static final Integer BATHROOMS_PF_ID = 18;
	private static final String IMAGE_URL_PREFIX = "http://www.hallocasa.com:64645/resources/images/properties/"; //TODO: Do dependent on url environment
	private static final String CURRENCY_PRES = "%1$s COP"; // TODO: Do dynamic
	
	/**
	 * Executes the transform process using constants attached
	 * @param property
	 * @param locale
	 */
	public FlatProperty transform(Property property, String locale) {
		FlatProperty flatProperty = new FlatProperty();
		for(PropertyField field : property.getFieldList()){
			if(field.getId().equals(TITLE_PF_ID)){
				flatProperty.setTitle(processByLocale(field.getFieldValueList(), locale));
			}
			if(field.getId().equals(BASIC_DESCRIPTION_PF_ID)){
				flatProperty.setBasicDescription(processByLocale(field.getFieldValueList(), locale));
			}
			if(field.getId().equals(LOC_DESCRIPTION_PF_ID)){
				flatProperty.setLocationDescription(processByLocale(field.getFieldValueList(), locale));
			}
			if(field.getId().equals(MARKET_PRICE_PF_ID)){
				flatProperty.setMarketPrice(processMarketPrice(field.getFieldValueList()));
			}
			if(field.getId().equals(IMAGE_PF_ID)){
				flatProperty.setUrlImage(processImage(field.getFieldValueList()));
			}
			if(field.getId().equals(ROOMS_PF_ID)){
				flatProperty.setRoomNumber(processRooms(field.getFieldValueList()));
			}
			if(field.getId().equals(BATHROOMS_PF_ID)){
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
		return IMAGE_URL_PREFIX + "/" + selectedPropertyFieldValue.getData1().getStrVal();
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
		Integer localeEquivalent = localeEquivalent(locale);
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
	
	public Integer localeEquivalent(String locale){
		if(locale == null){
			return EN_ID;
		}
		if(locale.equals("es") || locale.equals("ES")){
			return ES_ID;
		}
		if(locale.equals("en") || locale.equals("EN")){
			return EN_ID;
		}
		if(locale.equals("de") || locale.equals("DE")){
			return DE_ID;
		}
		return EN_ID;
	}
	
}
