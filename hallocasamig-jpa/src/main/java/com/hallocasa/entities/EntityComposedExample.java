package com.hallocasa.entities;

import com.hallocasa.entities.i.HallocasaEntity;

public class EntityComposedExample implements HallocasaEntity {

	private Integer identifier;
	
	private EntityExample example;
	
	private String ocurrences;

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public EntityExample getExample() {
		return example;
	}

	public void setExample(EntityExample example) {
		this.example = example;
	}

	public String getOcurrences() {
		return ocurrences;
	}

	public void setOcurrences(String ocurrences) {
		this.ocurrences = ocurrences;
	}
}
