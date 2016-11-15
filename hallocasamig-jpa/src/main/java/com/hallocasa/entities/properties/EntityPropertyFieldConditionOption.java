package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents an option to conditioning the showing of property field
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field_condition_option")
public class EntityPropertyFieldConditionOption implements HallocasaEntity {

	/**
	 * Property field condition option identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@JoinColumn(name = "property_field_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyField propertyField;
	
	@JoinColumn(name = "parent_property_field_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyField parentPropertyField;

	@Column(name = "parent_property_field_option_id")
	private Integer parentPropertyFieldOptionId;
	
	@Column(name = "condition_level")
	private Integer conditionLevel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntityPropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(EntityPropertyField propertyField) {
		this.propertyField = propertyField;
	}

	public EntityPropertyField getParentPropertyField() {
		return parentPropertyField;
	}

	public void setParentPropertyField(EntityPropertyField parentPropertyField) {
		this.parentPropertyField = parentPropertyField;
	}

	public Integer getParentPropertyFieldOptionId() {
		return parentPropertyFieldOptionId;
	}

	public void setParentPropertyFieldOptionId(Integer parentPropertyFieldOptionId) {
		this.parentPropertyFieldOptionId = parentPropertyFieldOptionId;
	}

	public Integer getConditionLevel() {
		return conditionLevel;
	}

	public void setConditionLevel(Integer conditionLevel) {
		this.conditionLevel = conditionLevel;
	}
}
