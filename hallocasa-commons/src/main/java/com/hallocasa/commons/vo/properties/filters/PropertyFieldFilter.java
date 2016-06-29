package com.hallocasa.commons.vo.properties.filters;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.commons.vo.interfaces.ValueObject;
import com.hallocasa.commons.vo.properties.PropertyFieldVO;

/**
 * Value Object that represents a filter sent by user in application
 * @author Alexander Villamil
 *
 */
public class PropertyFieldFilter implements Serializable, ValueObject {

	/**
	 * Generated key
	 */
	private static final long serialVersionUID = 880253091387403050L;

	private PropertyFieldVO propertyField;
	
	private PropertyFieldFilterType type;
	
	private Integer valueFrom;
	
	private Integer valueTo;
	
	private List<Integer> intValues;
	
	private List<String> stringValues;
	
	private Integer intValue;
	
	private String stringValue;
	
	private ComparatorType comparatorType;
	
	private String objectProperty;

	public PropertyFieldVO getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyFieldVO propertyField) {
		this.propertyField = propertyField;
	}

	public PropertyFieldFilterType getType() {
		return type;
	}

	public void setType(PropertyFieldFilterType type) {
		this.type = type;
	}

	public Integer getValueFrom() {
		return valueFrom;
	}

	public void setValueFrom(Integer valueFrom) {
		this.valueFrom = valueFrom;
	}

	public Integer getValueTo() {
		return valueTo;
	}

	public void setValueTo(Integer valueTo) {
		this.valueTo = valueTo;
	}

	public List<Integer> getIntValues() {
		return intValues;
	}

	public void setIntValues(List<Integer> intValues) {
		this.intValues = intValues;
	}

	public List<String> getStringValues() {
		return stringValues;
	}

	public void setStringValues(List<String> stringValues) {
		this.stringValues = stringValues;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public ComparatorType getComparatorType() {
		return comparatorType;
	}

	public void setComparatorType(ComparatorType comparatorType) {
		this.comparatorType = comparatorType;
	}

	public String getObjectProperty() {
		return objectProperty;
	}

	public void setObjectProperty(String objectProperty) {
		this.objectProperty = objectProperty;
	}
}
