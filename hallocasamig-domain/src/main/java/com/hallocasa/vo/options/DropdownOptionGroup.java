package com.hallocasa.vo.options;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing a group of dropdown options
 * @author avillamil
 */
public class DropdownOptionGroup implements ValueObject, Serializable {

	private static final long serialVersionUID = 9097874820229020422L;

	private Integer id;
	
	private String name;

	private Boolean dependsOnLang;
	
	private List<DropdownOption> dropdownOptionList;

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

	public List<DropdownOption> getDropdownOptionList() {
		return dropdownOptionList;
	}

	public void setDropdownOptionList(List<DropdownOption> dropdownOptionList) {
		this.dropdownOptionList = dropdownOptionList;
	}
}
