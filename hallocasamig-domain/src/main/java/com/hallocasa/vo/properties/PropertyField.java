package com.hallocasa.vo.properties;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.hcfilter.HcListerOption;
import com.hallocasa.vo.hcfilter.properties.PropertyDatatype;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldType;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValue;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueType;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOptionGroup;

public class PropertyField implements ValueObject, Serializable {

	private static final long serialVersionUID = -8762406905849463829L;

	/**
	 * Property field identifier
	 */
	private Integer id;

	private String name;

	private String lang;

	private Boolean basic;

	private Boolean primaryKey;

	private Boolean multilanguageValue;

	private PropertyFieldType propertyFieldType;

	private PropertyFieldValueType propertyFieldValueType;
	
	private List<PropertyFieldValue> fieldValueList;
	
	private DropdownOptionGroup dropdownOptionGroup;
	
	private String tooltipLang;

	private PropertyDatatype textType;
	
	private PropertyDatatype data1Type;
	
	private PropertyDatatype data2Type;
	
	private PropertyDatatype data3Type;
	
	private HcListerOption hcListerOption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Boolean getBasic() {
		return basic;
	}

	public void setBasic(Boolean basic) {
		this.basic = basic;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getMultilanguageValue() {
		return multilanguageValue;
	}

	public void setMultilanguageValue(Boolean multilanguageValue) {
		this.multilanguageValue = multilanguageValue;
	}

	public PropertyFieldType getPropertyFieldType() {
		return propertyFieldType;
	}

	public void setPropertyFieldType(PropertyFieldType propertyFieldType) {
		this.propertyFieldType = propertyFieldType;
	}

	public PropertyFieldValueType getPropertyFieldValueType() {
		return propertyFieldValueType;
	}

	public void setPropertyFieldValueType(PropertyFieldValueType propertyFieldValueType) {
		this.propertyFieldValueType = propertyFieldValueType;
	}

	public PropertyDatatype getTextType() {
		return textType;
	}

	public void setTextType(PropertyDatatype textType) {
		this.textType = textType;
	}

	public PropertyDatatype getData1Type() {
		return data1Type;
	}

	public void setData1Type(PropertyDatatype data1Type) {
		this.data1Type = data1Type;
	}

	public PropertyDatatype getData2Type() {
		return data2Type;
	}

	public void setData2Type(PropertyDatatype data2Type) {
		this.data2Type = data2Type;
	}

	public PropertyDatatype getData3Type() {
		return data3Type;
	}

	public void setData3Type(PropertyDatatype data3Type) {
		this.data3Type = data3Type;
	}

	public List<PropertyFieldValue> getFieldValueList() {
		return fieldValueList;
	}

	public void setFieldValueList(List<PropertyFieldValue> fieldValueList) {
		this.fieldValueList = fieldValueList;
	}

	public DropdownOptionGroup getDropdownOptionGroup() {
		return dropdownOptionGroup;
	}

	public void setDropdownOptionGroup(DropdownOptionGroup dropdownOptionGroup) {
		this.dropdownOptionGroup = dropdownOptionGroup;
	}

	public String getTooltipLang() {
		return tooltipLang;
	}

	public void setTooltipLang(String tooltipLang) {
		this.tooltipLang = tooltipLang;
	}

	public HcListerOption getHcListerOption() {
		return hcListerOption;
	}

	public void setHcListerOption(HcListerOption hcListerOption) {
		this.hcListerOption = hcListerOption;
	}
}
