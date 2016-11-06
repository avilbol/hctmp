package com.hallocasa.commons.test;

public enum EnumTest {
	A("a"), B("b"), C("great"), D("d");
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private EnumTest(String name) {
		this.name = name;
	}

	
	
}
