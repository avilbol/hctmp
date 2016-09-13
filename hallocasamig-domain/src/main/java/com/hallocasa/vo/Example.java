package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Clase de ejemplo
 * @author avillamil
 */
public class Example implements Serializable, ValueObject {

	private static final long serialVersionUID = 1699647469366463426L;

	private Integer identifier;
	
	private String description;

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Example [identifier=" + identifier + ", description=" + description + "]";
	}

	public Example() {
		super();
	}

	public Example(Integer identifier, String description) {
		super();
		this.identifier = identifier;
		this.description = description;
	}
}
