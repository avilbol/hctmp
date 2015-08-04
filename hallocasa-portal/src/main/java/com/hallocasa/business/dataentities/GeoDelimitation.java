/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.dataentities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.hallocasa.commons.beans.Identificable;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "geo_delimitation")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "GeoDelimitation.findAll", query = "SELECT g FROM GeoDelimitation g"),
		@NamedQuery(name = "GeoDelimitation.findByGeoDelimitationId", query = "SELECT g FROM GeoDelimitation g WHERE g.geoDelimitationId = :geoDelimitationId"),
		@NamedQuery(name = "GeoDelimitation.findByGeoDelimitationTypeId", query = "SELECT g FROM GeoDelimitation g WHERE g.geoDelimitationType.geoDelimitationTypeId = ?1 ORDER BY g.name"),
		@NamedQuery(name = "GeoDelimitation.findByGeoDelimitationParentId", query = "SELECT g FROM GeoDelimitation g WHERE g.geoDelimitation.geoDelimitationId = ?1 ORDER BY g.name"),
		@NamedQuery(name = "GeoDelimitation.findByName", query = "SELECT g FROM GeoDelimitation g WHERE g.name = :name"),
		@NamedQuery(name = "GeoDelimitation.findByGeoCode", query = "SELECT g FROM GeoDelimitation g WHERE g.geoCode = :geoCode") })
public class GeoDelimitation implements Serializable, Identificable<Integer> {

	public static final long COLOMBIA_ID = 5000;

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "geo_delimitation_id")
	private Integer geoDelimitationId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 120)
	@Column(name = "name")
	private String name;
	@Size(max = 45)
	@Column(name = "geo_code")
	private String geoCode;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "geoDelimitation", fetch = FetchType.LAZY)
	private List<Property> propertyList;
	@JoinColumn(name = "geo_delimitation_type_id", referencedColumnName = "geo_delimitation_type_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private GeoDelimitationType geoDelimitationType;
	@OneToMany(mappedBy = "geoDelimitation", fetch = FetchType.LAZY)
	private List<GeoDelimitation> geoDelimitationList;
	@JoinColumn(name = "geo_delimitation_parent", referencedColumnName = "geo_delimitation_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private GeoDelimitation geoDelimitation;

	public GeoDelimitation() {
	}

	public GeoDelimitation(Integer geoDelimitationId) {
		this.geoDelimitationId = geoDelimitationId;
	}

	public GeoDelimitation(Integer geoDelimitationId, String name) {
		this.geoDelimitationId = geoDelimitationId;
		this.name = name;
	}

	public Integer getGeoDelimitationId() {
		return geoDelimitationId;
	}

	public void setGeoDelimitationId(Integer geoDelimitationId) {
		this.geoDelimitationId = geoDelimitationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	@XmlTransient
	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

	public GeoDelimitationType getGeoDelimitationType() {
		return geoDelimitationType;
	}

	public void setGeoDelimitationType(GeoDelimitationType geoDelimitationType) {
		this.geoDelimitationType = geoDelimitationType;
	}

	@XmlTransient
	public List<GeoDelimitation> getGeoDelimitationList() {
		return geoDelimitationList;
	}

	public void setGeoDelimitationList(List<GeoDelimitation> geoDelimitationList) {
		this.geoDelimitationList = geoDelimitationList;
	}

	public GeoDelimitation getGeoDelimitation() {
		return geoDelimitation;
	}

	public void setGeoDelimitation(GeoDelimitation geoDelimitation) {
		this.geoDelimitation = geoDelimitation;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (geoDelimitationId != null ? geoDelimitationId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof GeoDelimitation)) {
			return false;
		}
		GeoDelimitation other = (GeoDelimitation) object;
		if ((this.geoDelimitationId == null && other.geoDelimitationId != null)
				|| (this.geoDelimitationId != null && !this.geoDelimitationId
						.equals(other.geoDelimitationId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.hallocasa.model.dataentities.GeoDelimitation[ geoDelimitationId="
				+ geoDelimitationId + " ]";
	}

	@Override
	public Integer getId() {
		return getGeoDelimitationId();
	}
}
