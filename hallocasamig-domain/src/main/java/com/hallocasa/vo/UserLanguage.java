package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class UserLanguage implements Serializable, ValueObject {

	private static final long serialVersionUID = -9017115319087943981L;

	private Language language;

	private Boolean isMainLanguage;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Boolean getIsMainLanguage() {
		return isMainLanguage;
	}

	public void setIsMainLanguage(Boolean isMainLanguage) {
		this.isMainLanguage = isMainLanguage;
	}
}
