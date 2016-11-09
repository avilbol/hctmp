package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hallocasa.entities.EntityHcFilterCondition;
import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name = "property_filter_condition_option")
public class EntityPropertyFilterConditionOption implements HallocasaEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "filter_condition_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityHcFilterCondition filterCondition;
	
	
	@JoinColumn(name = "property_field_option_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyFieldOption propertyFieldOption;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public EntityHcFilterCondition getFilterCondition() {
		return filterCondition;
	}


	public void setFilterCondition(EntityHcFilterCondition filterCondition) {
		this.filterCondition = filterCondition;
	}


	public EntityPropertyFieldOption getPropertyFieldOption() {
		return propertyFieldOption;
	}


	public void setPropertyFieldOption(EntityPropertyFieldOption propertyFieldOption) {
		this.propertyFieldOption = propertyFieldOption;
	}
}
