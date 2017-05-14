package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;

@Entity
@Table(name = "neighborhood")
@NamedQueries({
		@NamedQuery(name = EntityNeighborhood.QUERY_FIND_BY_CITY_ID, query = "select n from EntityNeighborhood n where n.city.id = ?1"),
		@NamedQuery(name = EntityNeighborhood.QUERY_FIND_GENERIC_USE, query = "select n from EntityNeighborhood n where n.genericUse = true") })
public class EntityNeighborhood implements HallocasaEntity {

	public static final String QUERY_FIND_BY_CITY_ID = "EntityCity.findByCityId";
	public static final String QUERY_FIND_GENERIC_USE = "EntityCity.findGenericUse";

	/**
	 * Neighborhood identifier
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@JoinColumn(name = "city_id", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private EntityCity city;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "generic_use")
	private Boolean genericUse;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "depends_on_lang")
	private Boolean dependsOnLang;

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

	public EntityCity getCity() {
		return city;
	}

	public void setCity(EntityCity city) {
		this.city = city;
	}

	public Boolean getGenericUse() {
		return genericUse;
	}

	public void setGenericUse(Boolean genericUse) {
		this.genericUse = genericUse;
	}

	public Boolean getDependsOnLang() {
		return dependsOnLang;
	}

	public void setDependsOnLang(Boolean dependsOnLang) {
		this.dependsOnLang = dependsOnLang;
	}
}

