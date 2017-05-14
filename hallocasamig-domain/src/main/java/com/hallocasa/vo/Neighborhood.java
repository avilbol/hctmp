package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class Neighborhood implements ValueObject, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Boolean genericUse;
	private Boolean dependsOnLang;

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

	public Boolean getGenericUse() {
		return genericUse;
	}

	public void setGenericUse(Boolean genericUse) {
		this.genericUse = genericUse;
	}

	public Boolean getDependsOnLang() {
		return dependsOnLang;
	}

	public void setDependsOnLang(Boolean dependsOnLang) {
		this.dependsOnLang = dependsOnLang;
	}
}
