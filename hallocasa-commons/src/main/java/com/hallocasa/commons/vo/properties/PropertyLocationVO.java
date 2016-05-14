package com.hallocasa.commons.vo.properties;

import java.io.Serializable;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.ValueObject;

public class PropertyLocationVO implements Serializable, ValueObject{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -8041019852937280384L;

	private Integer id;
	
	private MultiLanguageText name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultiLanguageText getName() {
		return name;
	}

	public void setName(MultiLanguageText name) {
		this.name = name;
	}
}
