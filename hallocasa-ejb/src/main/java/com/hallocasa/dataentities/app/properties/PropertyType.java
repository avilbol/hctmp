package com.hallocasa.dataentities.app.properties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;

/**
 * This entity represents the types which a property can be categorized
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_type")
public class PropertyType implements Serializable, HallocasaEntity{

	public static final String group_ = "propertyTypeGroup";
	
	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 6894467699350877717L;

	@JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PropertyTypeGroup propertyTypeGroup;
	
	/**
	 * Property type identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	@Convert(converter = MultiLanguageTextConverter.class)
	private MultiLanguageText name;

	public PropertyTypeGroup getPropertyTypeGroup() {
		return propertyTypeGroup;
	}

	public void setPropertyTypeGroup(PropertyTypeGroup propertyTypeGroup) {
		this.propertyTypeGroup = propertyTypeGroup;
	}

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
