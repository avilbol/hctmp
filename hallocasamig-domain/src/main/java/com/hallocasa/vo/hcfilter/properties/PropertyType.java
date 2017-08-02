package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PropertyType implements ValueObject, Serializable {

	private static final long serialVersionUID = -8762506705849423829L;

	private Integer id;
	
	private String name;
	
	private String lang;
	
	private Boolean isActive;
	
	private PropertyTypeGroup group;

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

	public PropertyTypeGroup getGroup() {
		return group;
	}

	public void setGroup(PropertyTypeGroup group) {
		this.group = group;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
