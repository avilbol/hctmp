package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing an option of a filter (typically dropdowns)
 */
public class HcFilterOption implements ValueObject, Serializable {

	private static final long serialVersionUID = -8521029765839743518L;
	private int id;
	private List<String> attrList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<String> attrList) {
		this.attrList = attrList;
	}
}
