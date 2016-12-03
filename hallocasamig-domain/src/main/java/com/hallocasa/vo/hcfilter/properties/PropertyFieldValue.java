package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PropertyFieldValue implements ValueObject, Serializable {

	private static final long serialVersionUID = -8762506905849463829L;
	
	private Integer bdid;
	private Integer identifier;
	private PropertyFieldValueSpec text;
	private PropertyFieldValueSpec data1;
	private PropertyFieldValueSpec data2;
	private PropertyFieldValueSpec data3;
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public PropertyFieldValueSpec getText() {
		return text;
	}
	public void setText(PropertyFieldValueSpec text) {
		this.text = text;
	}
	public PropertyFieldValueSpec getData1() {
		return data1;
	}
	public void setData1(PropertyFieldValueSpec data1) {
		this.data1 = data1;
	}
	public PropertyFieldValueSpec getData2() {
		return data2;
	}
	public void setData2(PropertyFieldValueSpec data2) {
		this.data2 = data2;
	}
	public PropertyFieldValueSpec getData3() {
		return data3;
	}
	public void setData3(PropertyFieldValueSpec data3) {
		this.data3 = data3;
	}
	public Integer getBdid() {
		return bdid;
	}
	public void setBdid(Integer bdid) {
		this.bdid = bdid;
	}
}
