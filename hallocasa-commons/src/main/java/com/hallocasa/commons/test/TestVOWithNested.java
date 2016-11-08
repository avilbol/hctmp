package com.hallocasa.commons.test;

import java.util.List;

public class TestVOWithNested {

	private Integer id;
	
	private String name;
	
	private List<Nested> objectList;

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

	public List<Nested> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Nested> objectList) {
		this.objectList = objectList;
	}
	
	
	
}
