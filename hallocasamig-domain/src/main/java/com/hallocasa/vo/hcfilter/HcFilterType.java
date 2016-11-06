package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO que representa un tipo de filtro a usar en un sistema de b�squeda
 * @author avillamil
 */
public class HcFilterType implements Serializable, ValueObject {

	private static final long serialVersionUID = 8385629228688826281L;
	private Integer id;
	private String name;

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
}
