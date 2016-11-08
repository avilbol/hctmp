package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.hallocasa.entities.i.HallocasaEntity;

@Entity
public class EntityHcFilterNature implements HallocasaEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;

	public Integer getId() {
		return id;
	}
}
