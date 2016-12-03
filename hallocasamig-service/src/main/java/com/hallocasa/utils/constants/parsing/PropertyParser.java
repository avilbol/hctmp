package com.hallocasa.utils.constants.parsing;

import static com.hallocasa.utils.resolvers.PropertyDataTypeRes.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.hallocasa.entities.EntityCountry;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.entities.properties.EntityPropertyFieldValue;
import com.hallocasa.entities.properties.EntityPropertyLocation;
import com.hallocasa.entities.properties.EntityPropertyProposal;
import com.hallocasa.entities.properties.EntityPropertyType;
import com.hallocasa.randomutils.RandomUtils;
import com.hallocasa.utils.constants.propertyfieldparsing.PropertyFieldValueConverter;
import com.hallocasa.vo.Country;
import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValue;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.hcfilter.properties.PropertyLocation;
import com.hallocasa.vo.hcfilter.properties.PropertyProposal;
import com.hallocasa.vo.hcfilter.properties.PropertyType;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyParser extends CustomizedParser {

	@Override
	public void initialize() {
	}

	@Override
	public void transform(ValueObject vo, HallocasaEntity ent) {
		EntityProperty entityProperty = (EntityProperty) ent;
		Property property = (Property) vo;
		property.setPropertyKey(new PropertyKey());
		property.getPropertyKey()
				.setPropertyType((PropertyType) HallocasaConvert.toValueObject(entityProperty.getPropertyType()));
		property.getPropertyKey().setPropertyLocation(
				(PropertyLocation) HallocasaConvert.toValueObject(entityProperty.getPropertyLocation()));
		property.getPropertyKey().setPropertyProposal(
				(PropertyProposal) HallocasaConvert.toValueObject(entityProperty.getPropertyProposal()));
		property.getPropertyKey().setCountry((Country) HallocasaConvert.toValueObject(entityProperty.getCountry()));
		Map<Integer, PropertyField> propertyFieldMap = new HashMap<Integer, PropertyField>();
		for (EntityPropertyFieldValue entFieldValue : entityProperty.getFieldValueList()) {
			EntityPropertyField pfield = entFieldValue.getPropertyField();
			if (propertyFieldMap.get(pfield.getId()) == null) {
				propertyFieldMap.put(pfield.getId(), (PropertyField) HallocasaConvert.toValueObject(pfield));
			}
			PropertyField propField = propertyFieldMap.get(pfield.getId());
			if(propField.getFieldValueList() == null){
				propField.setFieldValueList(new LinkedList<>());
			}
			PropertyFieldValue pfvalue = new PropertyFieldValue();
			pfvalue.setBdid(entFieldValue.getId());
			if (entFieldValue.getIdentifier() != null) {
				pfvalue.setIdentifier(Integer.parseInt(entFieldValue.getIdentifier()));
			}
			pfvalue.setText(getConverter(propField.getTextType()).toVo(entFieldValue.getText()));
			pfvalue.setData1(getConverter(propField.getData1Type()).toVo(entFieldValue.getData1()));
			pfvalue.setData2(getConverter(propField.getData2Type()).toVo(entFieldValue.getData2()));
			pfvalue.setData3(getConverter(propField.getData3Type()).toVo(entFieldValue.getData3()));
			propField.getFieldValueList().add(pfvalue);
		}
		property.setFieldList(new LinkedList<>(propertyFieldMap.values()));
	}

	@Override
	public void transform(HallocasaEntity ent, ValueObject vo) {
		EntityProperty entityProperty = (EntityProperty) ent;
		Property property = (Property) vo;
		String id = (property.getId() == null ? RandomUtils.alphanumericRandom(8) : property.getId());
		entityProperty.setId(id);
		entityProperty.setPropertyType(
				(EntityPropertyType) HallocasaConvert.toEntity(property.getPropertyKey().getPropertyType()));
		entityProperty.setPropertyLocation(
				(EntityPropertyLocation) HallocasaConvert.toEntity(property.getPropertyKey().getPropertyLocation()));
		entityProperty.setPropertyProposal(
				(EntityPropertyProposal) HallocasaConvert.toEntity(property.getPropertyKey().getPropertyProposal()));
		entityProperty.setCountry(
				(EntityCountry) HallocasaConvert.toEntity(property.getPropertyKey().getCountry()));
		entityProperty.setFieldValueList(new LinkedList<>());
		for(PropertyField pfield: property.getFieldList()){
			for(PropertyFieldValue pfieldvalue : pfield.getFieldValueList()){
				EntityPropertyFieldValue epfvalue = new EntityPropertyFieldValue();
				epfvalue.setPropertyField((EntityPropertyField) HallocasaConvert.toEntity(pfield));
				epfvalue.setId(pfieldvalue.getBdid());
				EntityProperty singleEntityProperty = new EntityProperty();
				singleEntityProperty.setId(entityProperty.getId());
				epfvalue.setProperty(singleEntityProperty);
				if (pfieldvalue.getIdentifier() != null) {
					epfvalue.setIdentifier(String.valueOf(pfieldvalue.getIdentifier()));
				}
				PropertyFieldValueConverter data1Converter = getConverter(pfield.getData1Type());
				PropertyFieldValueConverter data2Converter = getConverter(pfield.getData2Type());
				PropertyFieldValueConverter data3Converter = getConverter(pfield.getData3Type());
				PropertyFieldValueConverter textConverter = getConverter(pfield.getTextType());
				boolean data1RequiresExtra = getRequiresExtraParameters(pfield.getData1Type());
				boolean data2RequiresExtra = getRequiresExtraParameters(pfield.getData2Type());
				boolean data3RequiresExtra = getRequiresExtraParameters(pfield.getData3Type());
				boolean textRequiresExtra =  getRequiresExtraParameters(pfield.getTextType());
				String tempPrefix = "new";
				String data1 = data1RequiresExtra ? data1Converter.toEnt(pfieldvalue.getData1(), id, tempPrefix) 
						: data1Converter.toEnt(pfieldvalue.getData1());
				String data2 = data2RequiresExtra ? data2Converter.toEnt(pfieldvalue.getData2(), id, tempPrefix) 
						: data2Converter.toEnt(pfieldvalue.getData2());
				String data3 = data3RequiresExtra ? data3Converter.toEnt(pfieldvalue.getData3(), id, tempPrefix) 
						: data3Converter.toEnt(pfieldvalue.getData3());
				String text = textRequiresExtra ? textConverter.toEnt(pfieldvalue.getText(), id, tempPrefix) 
						: textConverter.toEnt(pfieldvalue.getText());
				epfvalue.setData1(data1);
				epfvalue.setData2(data2);
				epfvalue.setData3(data3);
				epfvalue.setText(text);
				entityProperty.getFieldValueList().add(epfvalue);
			}
		}
	}
}
