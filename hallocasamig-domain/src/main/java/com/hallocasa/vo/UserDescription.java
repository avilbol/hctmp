package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class UserDescription implements Serializable, ValueObject {

	private static final long serialVersionUID = -9017115319087943981L;

	private Language language;

	private String value;
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UserDescription [language=" + language + ", value=" + value + "]";
	}
}
