package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PropertyFieldRulePK {

	@Column(name = "property_type_group_id")
	private Integer propertyTypeGroupId;
	
	@Column(name = "property_location_id")
	private Integer propertyLocationId;
	
	@Column(name = "property_proposal_id")
	private Integer propertyProposalId;
	
	@Column(name = "country_id")
	private Integer countryId;

	public Integer getPropertyTypeGroupId() {
		return propertyTypeGroupId;
	}

	public void setPropertyTypeGroupId(Integer propertyTypeGroupId) {
		this.propertyTypeGroupId = propertyTypeGroupId;
	}

	public Integer getPropertyLocationId() {
		return propertyLocationId;
	}

	public void setPropertyLocationId(Integer propertyLocationId) {
		this.propertyLocationId = propertyLocationId;
	}

	public Integer getPropertyProposalId() {
		return propertyProposalId;
	}

	public void setPropertyProposalId(Integer propertyProposalId) {
		this.propertyProposalId = propertyProposalId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
}
