package com.hallocasa.entities.properties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hallocasa.entities.EntityCountry;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.i.HallocasaEntity;


/**
* This entity class represents a user's property
* @author Alexander Villamil
*/
@Entity
@Table(name = "property")
@NamedQueries({
	@NamedQuery(name = EntityProperty.QUERY_FIND_IN, query = "select p from EntityProperty p where p.id IN ?1"),
	@NamedQuery(name = EntityProperty.QUERY_FIND_BY_ID, query = "select p from EntityProperty p where p.id = ?1"),
	@NamedQuery(name = EntityProperty.QUERY_DELETE_BY_ID, query = "delete from EntityProperty p where p.id = ?1")
	})
public class EntityProperty implements Serializable, HallocasaEntity {

	public static final String QUERY_FIND_BY_ID = "EntityProperty.QueryFindById";
	
	public static final String QUERY_FIND_IN = "EntityProperty.QueryFindIn";
	
	public static final String QUERY_DELETE_BY_ID = "EntityProperty.QueryDeleteById";
	
	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 8086831618480962012L;

	/**
	 * Property identifier
	 */
	@Id
    @Column(name = "property_id")
	private String id;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityUser user;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "publish_date")
	private Date publishDate;
	
	@JoinColumn(name = "property_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyType propertyType;
	
	@JoinColumn(name = "property_proposal_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyProposal propertyProposal;
	
	@JoinColumn(name = "property_location_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyLocation propertyLocation;
	
	@JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCountry country;
	
	@OneToMany(mappedBy="property", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EntityPropertyFieldValue> fieldValueList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}

	public EntityPropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(EntityPropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public EntityPropertyProposal getPropertyProposal() {
		return propertyProposal;
	}

	public void setPropertyProposal(EntityPropertyProposal propertyProposal) {
		this.propertyProposal = propertyProposal;
	}

	public EntityPropertyLocation getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(EntityPropertyLocation propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public EntityCountry getCountry() {
		return country;
	}

	public void setCountry(EntityCountry country) {
		this.country = country;
	}

	public List<EntityPropertyFieldValue> getFieldValueList() {
		return fieldValueList;
	}

	public void setFieldValueList(List<EntityPropertyFieldValue> fieldValueList) {
		this.fieldValueList = fieldValueList;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}
