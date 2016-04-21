package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hallocasa.dataentities.converters.DatabaseBooleanConverter;

/**
 * This entity represents a field of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field_rule")
public class PropertyFieldRule {

	@EmbeddedId
	PropertyFieldRulePK propertyFieldRulePK;
	
	@Column(name="render_field")
	@Convert(converter = DatabaseBooleanConverter.class)
	Boolean renderField;

	public PropertyFieldRulePK getPropertyFieldRulePK() {
		return propertyFieldRulePK;
	}

	public void setPropertyFieldRulePK(PropertyFieldRulePK propertyFieldRulePK) {
		this.propertyFieldRulePK = propertyFieldRulePK;
	}

	public Boolean isRenderField() {
		return renderField;
	}

	public void setRenderField(Boolean renderField) {
		this.renderField = renderField;
	}
}
