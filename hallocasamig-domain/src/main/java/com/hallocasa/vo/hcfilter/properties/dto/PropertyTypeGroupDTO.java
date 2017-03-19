package com.hallocasa.vo.hcfilter.properties.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyType;
import com.hallocasa.vo.hcfilter.properties.PropertyTypeGroup;

public class PropertyTypeGroupDTO implements Serializable {

	private static final long serialVersionUID = -8762527707643403829L;

	private Integer id;

	private String name;
	
	private List<PropertyType> propertyTypeList;
	
	public PropertyTypeGroupDTO() {
		super();
		this.propertyTypeList = new LinkedList<PropertyType>();
	}
	
	public PropertyTypeGroupDTO(PropertyTypeGroup group) {
		super();
		this.propertyTypeList = new LinkedList<PropertyType>();
		this.id = group.getId();
		this.name = group.getName();
	}

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

	public List<PropertyType> getPropertyTypeList() {
		return propertyTypeList;
	}

	public void setPropertyTypeList(List<PropertyType> propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}
}
