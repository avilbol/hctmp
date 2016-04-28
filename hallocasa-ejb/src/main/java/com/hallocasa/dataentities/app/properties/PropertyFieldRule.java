package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.hallocasa.dataentities.app.Country;
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
	
	@MapsId("propertyTypeGroupId")
	@JoinColumn(name = "property_type_group_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	PropertyTypeGroup propertyTypeGroup;
	
	@MapsId("propertyLocationId")
	@JoinColumn(name = "property_location_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	PropertyLocation propertyLocation;
	
	@MapsId("propertyProposalId")
	@JoinColumn(name = "property_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	PropertyProposal propertyProposal;
	
	@MapsId("countryId")
	@JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	Country country;
	
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

	public PropertyTypeGroup getPropertyTypeGroup() {
		return propertyTypeGroup;
	}

	public void setPropertyTypeGroup(PropertyTypeGroup propertyTypeGroup) {
		this.propertyTypeGroup = propertyTypeGroup;
	}

	public PropertyLocation getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(PropertyLocation propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public PropertyProposal getPropertyProposal() {
		return propertyProposal;
	}

	public void setPropertyProposal(PropertyProposal propertyProposal) {
		this.propertyProposal = propertyProposal;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Boolean getRenderField() {
		return renderField;
	}
	
	
}
