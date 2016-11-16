package com.hallocasa.entities.properties;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;

/**
 * This entity represents a group for a number of dropdown options
 * @author Alexander Villamil
 */
@Entity
@Table(name = "dropdown_option_group")
public class EntityDropdownOptionGroup implements HallocasaEntity {

	/**
	 * Dropdown option group identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="depends_on_lang")
	@Convert(converter = HcBooleanConverter.class)
	private Boolean dependsOnLang;
	
	@OneToMany(mappedBy="dropdownOptionGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EntityDropdownOption> dropdownOptionList;
	
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

	public Boolean getDependsOnLang() {
		return dependsOnLang;
	}

	public void setDependsOnLang(Boolean dependsOnLang) {
		this.dependsOnLang = dependsOnLang;
	}

	public List<EntityDropdownOption> getDropdownOptionList() {
		return dropdownOptionList;
	}

	public void setDropdownOptionList(List<EntityDropdownOption> dropdownOptionList) {
		this.dropdownOptionList = dropdownOptionList;
	}
}
