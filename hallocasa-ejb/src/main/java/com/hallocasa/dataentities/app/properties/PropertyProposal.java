package com.hallocasa.dataentities.app.properties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;

/**
 * This entity represents a proposal (buy/rent f.e) of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_proposal")
public class PropertyProposal implements Serializable, HallocasaEntity{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -3056821182931140344L;

	/**
	 * Property proposal identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	@Convert(converter = MultiLanguageTextConverter.class)
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
