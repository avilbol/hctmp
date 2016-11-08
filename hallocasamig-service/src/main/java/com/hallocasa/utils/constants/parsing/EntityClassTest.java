package com.hallocasa.utils.constants.parsing;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class EntityClassTest {

	private int id;
	
	private String attr1;
	
	private String attr2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException{
		EntityClassTest gg = new EntityClassTest();
		gg.setAttr1("attr1");
		gg.setAttr2("attr2");
		gg.setId(23);
		ClassTest classTest = new ClassTestComp();
		if(gg.getAttr2().equals("attr2")){
			ClassTest ct = (ClassTestComp) classTest;
			BeanUtils.copyProperties(ct, gg);
			System.out.println(((ClassTestComp)ct).getAttr2());
		}
		
	}
}
