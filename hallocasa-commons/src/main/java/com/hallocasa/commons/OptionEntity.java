package com.hallocasa.commons;

import com.hallocasa.commons.i18n.MultiLanguageText;

/**
 * Object though for encapsulate an identifier and a given name
 * @author Alexander Villamil
 *
 */
public class OptionEntity {

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
