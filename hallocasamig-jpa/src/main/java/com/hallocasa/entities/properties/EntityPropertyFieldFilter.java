package com.hallocasa.entities.properties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name = "property_field_filter")
public class EntityPropertyFieldFilter implements HallocasaEntity {

	@Id
	@JoinColumn(name = "filter_id", referencedColumnName = "id")
	@OneToOne(fetch = FetchType.LAZY)
	private EntityHcFilter filter;
	
	@JoinColumn(name = "property_field_id", referencedColumnName = "id")
	@OneToOne(fetch = FetchType.LAZY)
	private EntityPropertyField propertyField;

	public EntityHcFilter getFilter() {
		return filter;
	}

	public void setFilter(EntityHcFilter filter) {
		this.filter = filter;
	}

	public EntityPropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(EntityPropertyField propertyField) {
		this.propertyField = propertyField;
	}
}
