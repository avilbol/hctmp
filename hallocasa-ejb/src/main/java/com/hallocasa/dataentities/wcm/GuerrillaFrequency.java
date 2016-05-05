/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities.wcm;

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

import com.hallocasa.dataentities.interfaces.TypeInterface;
import com.hallocasa.commons.Language;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "guerrilla_frequency")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "GuerrillaFrequency.findAll", query = "SELECT g FROM GuerrillaFrequency g"),
		@NamedQuery(name = "GuerrillaFrequency.findByGuerrillaFrequencyId", query = "SELECT g FROM GuerrillaFrequency g WHERE g.guerrillaFrequencyId = :guerrillaFrequencyId"),
		@NamedQuery(name = "GuerrillaFrequency.findByName", query = "SELECT g FROM GuerrillaFrequency g WHERE g.name = :name") })
public class GuerrillaFrequency implements Serializable, TypeInterface {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "guerrilla_frequency_id")
	private Integer guerrillaFrequencyId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "name")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "guerrillaFrequency", fetch = FetchType.LAZY)
	private List<PropertyEnvirontment> propertyEnvirontmentList;
	@JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Translation translationName;

	public GuerrillaFrequency() {
	}

	public GuerrillaFrequency(Integer guerrillaFrequencyId) {
		this.guerrillaFrequencyId = guerrillaFrequencyId;
	}

	public GuerrillaFrequency(Integer guerrillaFrequencyId, String name) {
		this.guerrillaFrequencyId = guerrillaFrequencyId;
		this.name = name;
	}

	public Integer getGuerrillaFrequencyId() {
		return guerrillaFrequencyId;
	}

	public void setGuerrillaFrequencyId(Integer guerrillaFrequencyId) {
		this.guerrillaFrequencyId = guerrillaFrequencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public List<PropertyEnvirontment> getPropertyEnvirontmentList() {
		return propertyEnvirontmentList;
	}

	public void setPropertyEnvirontmentList(
			List<PropertyEnvirontment> propertyEnvirontmentList) {
		this.propertyEnvirontmentList = propertyEnvirontmentList;
	}

	public Translation getTranslationName() {
		return translationName;
	}

	public void setTranslationName(Translation translationName) {
		this.translationName = translationName;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (guerrillaFrequencyId != null ? guerrillaFrequencyId.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof GuerrillaFrequency)) {
			return false;
		}
		GuerrillaFrequency other = (GuerrillaFrequency) object;
		if ((this.guerrillaFrequencyId == null && other.guerrillaFrequencyId != null)
				|| (this.guerrillaFrequencyId != null && !this.guerrillaFrequencyId
						.equals(other.guerrillaFrequencyId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.hallocasa.model.dataentities.GuerrillaFrequency[ guerrillaFrequencyId="
				+ guerrillaFrequencyId + " ]";
	}

	@Override
	public Integer getId() {
		return getGuerrillaFrequencyId();
	}

	/* (non-Javadoc)
	 * @see com.hallocasa.commons.Listable#getLabel(com.hallocasa.model.Language)
	 */
	@Override
	public String getLabel(Language language) {
		return getTranslationName().getText(language);
	}

}
