package com.hallocasa.vo.properties;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing an option of a multiple option property field 
 * (typically dropdowns)
 */
public class PropertyFieldOption implements ValueObject, Serializable {

	private static final long serialVersionUID = -8521029765839743518L;
	private List<String> attrList;
	private List<String> extendedAttrList;
	
	public List<String> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<String> attrList) {
		this.attrList = attrList;
	}
	public List<String> getExtendedAttrList() {
		return extendedAttrList;
	}
	public void setExtendedAttrList(List<String> extendedAttrList) {
		this.extendedAttrList = extendedAttrList;
	}
}
