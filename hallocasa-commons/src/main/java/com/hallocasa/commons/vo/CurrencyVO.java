package com.hallocasa.commons.vo;

import java.io.Serializable;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for currency representation
 * @author avillamil
 *
 */
public class CurrencyVO  implements ValueObject, Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -5728762206380375694L;

	private Integer id;
	
	private MultiLanguageText name;

	private String abbreviation;

	private String prefix;
	
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
