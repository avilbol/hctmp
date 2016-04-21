package com.hallocasa.dataentities.app.properties;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hallocasa.dataentities.app.Country;
import com.hallocasa.dataentities.app.User;

/**
* This entity class represents a user's property
* @author Alexander Villamil
*/
@Entity
@Table(name = "property")
@NamedQueries({
   @NamedQuery(name = Property.QUERY_FIND_BY_USER_ID,
           query = "select p from Property p where p.user = ?1"),
})
public class Property {

	/**
	 * Property identifier
	 */
	@Id
    @Column(name = "property_id")
	private String id;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = "property_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PropertyType propertyType;
	
	@JoinColumn(name = "property_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PropertyProposal propertyProposal;
	
	@JoinColumn(name = "property_location_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PropertyLocation propertyLocation;
	
	@JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Country country;
	
	@OneToMany(mappedBy="property")
	private List<PropertyFieldValue> fieldValueList;
	
	/**
	 * Consulta que obtiene los datos de la propiedad a trav&eacute;s del
	 * identificador de usuario
	 */
	public static final String QUERY_FIND_BY_USER_ID = "Property.findByUserId";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public PropertyProposal getPropertyProposal() {
		return propertyProposal;
	}

	public void setPropertyProposal(PropertyProposal propertyProposal) {
		this.propertyProposal = propertyProposal;
	}

	public PropertyLocation getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(PropertyLocation propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PropertyFieldValue> getFieldValueList() {
		return fieldValueList;
	}

	public void setFieldValueList(List<PropertyFieldValue> fieldValueList) {
		this.fieldValueList = fieldValueList;
	}
}
