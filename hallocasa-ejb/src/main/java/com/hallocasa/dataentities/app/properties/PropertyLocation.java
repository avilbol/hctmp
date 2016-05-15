package com.hallocasa.dataentities.app.properties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.interfaces.HallocasaEntity;
import com.hallocasa.dataentities.converters.MultiLanguageTextConverter;

/**
 * This entity represents a possible location of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_location")
@NamedQueries({
    @NamedQuery(name= PropertyLocation.QUERY_FIND_ALL,
            query = "select pl from PropertyLocation pl")
})
public class PropertyLocation implements Serializable, HallocasaEntity {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -418701865775757294L;

	/**
	 * Query for search every property location
	 */
	public static final String QUERY_FIND_ALL = "PropertyLocation.findAll";
	
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
