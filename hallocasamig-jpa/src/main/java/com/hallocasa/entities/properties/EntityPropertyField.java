package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;

/**
 * This entity represents a field of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field")
@NamedQueries({
	@NamedQuery(name = EntityPropertyField.QUERY_FIND_BY_FILTER, 
			query = "select f from EntityPropertyField f where f.id IN ("
					+ "select ff.propertyField.id from EntityPropertyFieldFilter ff "
					+ "where ff.filter.id = ?1)")})
public class EntityPropertyField implements HallocasaEntity {

	/**
	 * Query to find all filters
	 */
	public static final String QUERY_FIND_BY_FILTER = "EntityPropertyField.queryFindByFilter";
	
	/**
	 * Property field identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name="basic")
	private Boolean basic;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getBasic() {
		return basic;
	}

	public void setBasic(Boolean basic) {
		this.basic = basic;
	}
}
