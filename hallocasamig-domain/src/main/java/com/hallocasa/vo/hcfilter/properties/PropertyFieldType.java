package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PropertyFieldType implements ValueObject, Serializable {

	private static final long serialVersionUID = -8762506905849463829L;

	private Integer id;
	
	private String name;

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
}
