package com.hallocasa.commons.vo.properties;

import java.io.Serializable;

import com.hallocasa.commons.i18n.MultiLanguageText;


/**
 * Value object for properties proposal
 * @author avillamil
 *
 */
public class PropertyProposalVO implements Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -3721224192215566206L;

	/**
	 * Property proposal identifier
	 */
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
