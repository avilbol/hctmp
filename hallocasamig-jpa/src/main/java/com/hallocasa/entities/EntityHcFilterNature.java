package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name="filter_nature")
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
