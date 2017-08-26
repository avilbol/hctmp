package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * Entity for preferred locale and currency settings
 * @author Alexander
 */
@Entity
@Table(name = "preferred_settings")
@NamedQueries({
		@NamedQuery(name = EntityPreferredSetting.QUERY_FIND, query = "select e from EntityPreferredSetting e") })
public class EntityPreferredSetting implements HallocasaEntity {

	/**
	 * Query for find all registries
	 */
	public static final String QUERY_FIND = "EntityPreferredSetting.find";
	
	/**
	 * Object identifier
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	/**
	 * Language locale preferred in country
	 */
	@Column(name = "locale")
	private String locale;

	/**
	 * Country of preferred settings
	 */
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCountry country;
	
	/**
	 * Currency for the country
	 */
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCurrency firstCurrency;
	
	/**
	 * Second possible currency for the country
	 */
	@JoinColumn(name = "second_currency_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCurrency secondCurrency;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public EntityCountry getCountry() {
		return country;
	}

	public void setCountry(EntityCountry country) {
		this.country = country;
	}

	public EntityCurrency getFirstCurrency() {
		return firstCurrency;
	}

	public void setFirstCurrency(EntityCurrency firstCurrency) {
		this.firstCurrency = firstCurrency;
	}

	public EntityCurrency getSecondCurrency() {
		return secondCurrency;
	}

	public void setSecondCurrency(EntityCurrency secondCurrency) {
		this.secondCurrency = secondCurrency;
	}
}
